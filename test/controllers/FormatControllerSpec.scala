package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

class FormatControllerSpec
    extends PlaySpec
    with GuiceOneAppPerTest
    with Injecting {

  "FormatController PUT" should {

    "upload a source code file" in {
      val input = "object   Main extends  App {}"
      val output = "object Main extends App {}"

      val request = FakeRequest(PUT, "/stdin")
        .withTextBody(input)
      val format = route(app, request).get

      status(format) mustBe OK
      contentType(format) mustBe Some("text/plain")
      contentAsString(format) must include(output)
    }

    "upload a broken text file" in {
      val input = "object   Main extendZZZZ  App {"
      val output = "object   Main extendZZZZ  App {"

      val request = FakeRequest(PUT, "/stdin")
        .withTextBody(input)
      val format = route(app, request).get

      status(format) mustBe OK
      contentType(format) mustBe Some("text/plain")
      contentAsString(format) must include(output)
    }

  }

}
