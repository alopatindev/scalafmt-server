package controllers

import javax.inject._

import org.scalafmt.{Formatted, Scalafmt}

import play.api._
import play.api.mvc._

import scala.io.Source

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  def index(unused: String) = Action(parse.temporaryFile) { request => {
    val path = request.body.path
    val filename = path.toString

    val file = Source.fromFile(filename)
    val text = file.mkString
    file.close()

    path.toFile.delete()

    Scalafmt.format(text) match {
      case Formatted.Success(formattedText) =>
        Ok(formattedText)
      case Formatted.Failure(e) =>
        Logger.error("Failed to format", e)
        Ok(text)
      }
    }
  }

}
