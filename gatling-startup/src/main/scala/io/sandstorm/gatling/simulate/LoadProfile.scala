package io.sandstorm.gatling.simulate

import java.io.File

import io.sandstorm.gatling.AppConfig
import org.json4s._
import org.json4s.native.JsonMethods._

object LoadProfile {

  private val jsonObj = parse(FileInput(new File(AppConfig.loadProfileConfFile)))
  // import implicit conversion of Date,
  // if the default data format isn't what you want,
  // you have to customize it
  implicit val formats = DefaultFormats
  // Convert org.json4s.JsonAST.JObject to LoadProfile
  private val loadProfile = jsonObj.extract[LoadProfile]

  def scenarioRepeatTimes(): Int = loadProfile.scnRepeatTimes

  def userInjectSteps(): List[UserInjectStep] = loadProfile.userInjectSteps

  def rpsThrotSteps(): List[RpsThrotStep] = loadProfile.rpsThrotSteps

}

case class LoadProfile(scnRepeatTimes: Int, userInjectSteps: List[UserInjectStep], rpsThrotSteps: List[RpsThrotStep])

case class UserInjectStep(totalUsers: Int, rateUps: Int, duration: Int)

case class RpsThrotStep(rampTime: Int, toRps: Int, holdTime: Int)

