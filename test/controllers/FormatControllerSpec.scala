package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class FormatControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "FormatController GET" should {

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val format = route(app, request).get

      status(format) mustBe OK
      contentType(format) mustBe Some("text/html")
      contentAsString(format) must include ("Welcome to Play")
    }
  }
}
