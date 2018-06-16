package io.sandstorm.gatling.simulate

import java.util.{Collection => JColl, Map => JMap}

import io.gatling.core.Predef._
import io.gatling.core.body.StringBody
import io.gatling.core.session.Session
import io.sandstorm.gatling.JacksonJson

import scala.collection.JavaConversions._

trait SimulationHelper {

  private val attr_name_scn_data = "scn_data"

  def transformJsonScnData(session: Session): Session = {
    val scenarioDataMap = toScala(session(attr_name_scn_data).as[JMap[String, Any]])
      .asInstanceOf[Map[String, Any]]
    val scenarioData: Map[String, RequestData] = convert(scenarioDataMap)
    session.set(attr_name_scn_data, scenarioData)
  }

  def requestData(requestName: String, session: Session): RequestData = {
    val map = session(attr_name_scn_data)
      .as[Map[String, RequestData]]
    map(requestName)
  }

  def reqParamsAsStringBody(requestName: String): StringBody = {
    StringBody(session => JacksonJson.toJson(requestData(requestName, session).params))
  }

  def reqBodyAsStringBody(requestName: String): StringBody = {
    StringBody(session => JacksonJson.toJson(requestData(requestName, session).body))
  }

  def reqBodyAsStringBody(requestName: String, bodyReviser: (Session, Map[String, Any]) => Map[String, Any]): StringBody = {
    StringBody(session => {
      val theBody = requestData(requestName, session).body
      JacksonJson.toJson(bodyReviser(session, theBody))
    })
  }

  private def convert(scenarioData: Map[String, Any]): Map[String, RequestData] = {
    scenarioData.mapValues(value => transform(value.asInstanceOf[Map[String, Any]]))
  }

  private def transform(requestData: Map[String, Any]): RequestData = {
    val cookies = requestData.getOrElse[Any]("cookies", Map.empty).asInstanceOf[Map[String, String]]
    val headers = requestData.getOrElse[Any]("headers", Map.empty).asInstanceOf[Map[String, String]]
    val pathVariables = requestData.getOrElse[Any]("path_variables", Map.empty).asInstanceOf[Map[String, String]]
    val params = requestData.getOrElse[Any]("params", Map.empty).asInstanceOf[Map[String, Any]]
    val body = requestData.getOrElse[Any]("body", Map.empty).asInstanceOf[Map[String, Any]]
    RequestData(pathVariables, cookies, headers, params, body)
  }

  private def toScala(value: Any): Any = {
    value match {
      case jMap: JMap[_, _] => jMap.map(kv => (kv._1, toScala(kv._2))).toMap
      case jColl: JColl[_] => jColl.map(item => toScala(item)).toList
      case _ => value
    }
  }
}