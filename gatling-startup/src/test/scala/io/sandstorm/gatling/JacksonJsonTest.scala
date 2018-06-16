package io.sandstorm.gatling

object JacksonJsonTest {

  def main(args: Array[String]): Unit = {

    /*
     * (Un)marshalling a simple map
     */

    val originalMap = Map("a" -> List(1, 2), "b" -> List(3, 4, 5), "c" -> List())
    val json = JacksonJson.toJson(originalMap)
    println(s"json: $json")
    val genericMap = JacksonJson.toMap[Seq[Int]](json)
    println(s"genericMap: $genericMap")

    /*
     * Unmarshalling to a specific type of Map
     */

    val specificMap = JacksonJson.fromJson[collection.mutable.Map[Symbol, Seq[Int]]](json)
    println(s"specificMap: $specificMap")

    /*
     * (Un)marshalling nested case classes
     */

    val cary = Person("Cary", 26)
    val martin = Person("Martin", 54)
    val originalGroup = Group("Scala ppl", Seq(cary, martin), martin)
    //val originalGroup: Group = Group("Scala ppl", List(Person("Bob",26), Person("Bill",54)),Person("Bob",54))
    val groupJson = JacksonJson.toJson(originalGroup)
    println(s"groupJson: $groupJson")
    val group = JacksonJson.fromJson[Group](groupJson)
    println(s"group: $group")
  }

}

case class Person(name: String, age: Int)

case class Group(name: String, persons: Seq[Person], leader: Person)
