package io.sandstorm.gatling.simulate

case class RequestData(
  pathVariables: Map[String, String] = Map.empty,
  cookies: Map[String, String] = Map.empty,
  headers: Map[String, String] = Map.empty,
  params: Map[String, Any] = Map.empty,
  body: Map[String, Any] = Map.empty)
