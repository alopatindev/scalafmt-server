package controllers

import javax.inject._

import org.scalafmt.{Formatted, Scalafmt}

import play.api._
import play.api.mvc._

import scala.io.Source

@Singleton
class FormatController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {

  def index(unused: String) = Action(parse.temporaryFile) { request =>
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
