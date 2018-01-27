import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import sch.db.AerospikeImpl
import sch.routes.HttpRoutes
import sct.utils.Seeder

object SchBoot extends App {

  implicit val system: ActorSystem = ActorSystem("root")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val dbProvider = new AerospikeImpl()

  val httpRoutes = new HttpRoutes(dbProvider)
  Seeder.readStudents()
  Seeder.readCourses()
  Http().bindAndHandle(httpRoutes.routes, "localhost", 8080)
}