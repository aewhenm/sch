import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import sch.db.AerospikeImpl
import sch.routes.HttpRoutes

object SchBoot extends App {

  implicit val system: ActorSystem = ActorSystem("root")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val dbProvider = new AerospikeImpl()
  val httpRoutes = new HttpRoutes(dbProvider)

  dbProvider.seedData()
  dbProvider.generateSchedules()

  private val interface = "10.42.0.1"
  private val port = 8080

  dbProvider.getOptimizedSchedule

  system.log.info("Server started at {}:{}", interface, port)
  Http().bindAndHandle(httpRoutes.routes, interface, port)
}
