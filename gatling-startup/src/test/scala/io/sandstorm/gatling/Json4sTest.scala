package io.sandstorm.gatling

import io.sandstorm.gatling.Json4s
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class Json4sTest extends FlatSpec with Matchers {

  "A PopulatingRequestData object" should "be successfully serialized to its json representation" in {
    val populatingData: RequestData = new RequestData(
      headers = Map("Authorization" -> "abc123", "Content-Type" -> "application/json; charset=UTF-8"),
      cookies = Map("store_id" -> "store_123"),
      body = Map[String, Any]("items" -> List("item_1", "item_2", "item_3"))
    )
    val expected = """{
                     |"pathVariables":{},
                     |"cookies":{
                     |"store_id":"store_123"
                     |},
                     |"headers":{
                     |"Authorization":"abc123",
                     |"Content-Type":"application/json; charset=UTF-8"
                     |},
                     |"params":{},
                     |"body":{
                     |"items":[
                     |"item_1",
                     |"item_2",
                     |"item_3"
                     |]
                     |}
                     |}""".stripMargin.replaceAll("\n", "")
    println(expected)
    Json4s.toJson(populatingData) should be (expected)
  }

  "A Book object" should "be successfully serialized to its json representation" in {
    val book = Book("The Lord of the Rings", "J. R. R. Tolkien")
    val expected = """{"title":"The Lord of the Rings","author":"J. R. R. Tolkien"}"""
    Json4s.toJson(book) should be (expected)
  }

  "A json string" should "be successfully serialized to a Map that denotes a book" in {
    val bookJson: String = """{ "title": "The Lord of the Rings", "author":"J. R. R. Tolkien" }"""
    val bookMap: mutable.Map[String, Any] = Json4s.fromJson[mutable.Map[String, Any]](bookJson)
    bookMap("title") shouldBe("The Lord of the Rings")
    bookMap("author") shouldBe("J. R. R. Tolkien")
    bookMap += ("price" -> 10)
    println(bookMap)
  }

  "A json string" should "be successfully serialized to an Book object" in {
    val bookJson: String = """{ "title": "The Lord of the Rings", "author":"J. R. R. Tolkien" }"""
    val book: Book = Json4s.fromJson[Book](bookJson)
    book.title shouldBe("The Lord of the Rings")
    book.author shouldBe("J. R. R. Tolkien")
  }

}

case class Book(title: String, author: String)

case class RequestData(
                        pathVariables: Map[String, String] = Map.empty,
                        cookies: Map[String, String] = Map.empty,
                        headers: Map[String, String] = Map.empty,
                        params: Map[String, Any] = Map.empty,
                        body: Map[String, Any] = Map.empty)

