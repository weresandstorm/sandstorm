package io.sandstorm.gatling

import com.typesafe.config.{Config, ConfigFactory}

object AppConfig {

  private val config: Config = ConfigFactory.load("application.properties")

  val loadProfileConfFile: String = config.getString("load-profile.conf.file")

}