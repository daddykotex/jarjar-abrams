package example

import shaded.org.json4s._
import shaded.org.json4s.jackson._

final case class Thing(params: String)

object ApplicationMain extends App {
  implicit val formats: Formats = DefaultFormats

  val thing = Thing("paramsValue")
  val json: String = Json4sHandler.write(thing)
  println(json)
  assert(json == "")
  assert(Json4sHandler.read[Thing](json) == thing)
}



object Json4sHandler {
  def read[A](raw: String)(implicit formats: Formats, mf: Manifest[A]): A = {
    Serialization.read[A](raw)
  }

  def write[A <: AnyRef](value: A)(implicit formats: Formats): String = {
    Serialization.write(value)
  }
}