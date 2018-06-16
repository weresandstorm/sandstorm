package io.sandstorm.gatling

import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read, write}

object Json4s {

  implicit val formats = Serialization.formats(NoTypeHints)

  def toJson(value: AnyRef): String = write(value)

  def fromJson[T: Manifest](json: String): T = read[T](json)

}
