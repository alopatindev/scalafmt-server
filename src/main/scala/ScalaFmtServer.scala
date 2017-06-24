import org.scalafmt.Scalafmt.format

import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}
import java.net.{InetSocketAddress, URLDecoder}

object ScalaFmtServer {

  class RequestHandler extends HttpHandler {
    def decodeAndFormat(exchange: HttpExchange): String = {
      val rawInput = exchange
        .getRequestURI
        .getRawQuery
        .split("=")
        .last

      val input = URLDecoder.decode(rawInput, "UTF-8")

      try {
        format(input).get
      } catch {
        case e: Exception =>
          println(e)
          input
      }
    }

    override def handle(exchange: HttpExchange) {
      try {
        val output = decodeAndFormat(exchange)
        val bytes = output.getBytes
        exchange.sendResponseHeaders(200, bytes.length)
        val outputStream = exchange.getResponseBody()
        outputStream.write(bytes)
        outputStream.close()
      } catch {
        case e: Exception => println(e)
      }
    }
  }

  def parsePort(args: Array[String]): Int = try {
    args.head.toInt
  } catch {
    case e: Exception => throw new Exception("You need to specify port")
  }

  def main(args: Array[String]) {
    try {
      val port = parsePort(args)
      val host = "127.0.0.1"
      val server = HttpServer.create(new InetSocketAddress(host, port), 0)
      server.createContext("/", new RequestHandler())
      server.start()
    } catch {
      case e: Exception => println(e)
    }
  }

}
