import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import sch.routes.HttpRoutes

object SchBoot extends App {

  implicit val system: ActorSystem = ActorSystem("root")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val httpRoutes = new HttpRoutes()

  Http().bindAndHandle(httpRoutes.routes, "localhost", 8080)
}