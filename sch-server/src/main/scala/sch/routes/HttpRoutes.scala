package sch.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import sch.domain.Course.Course
import sch.domain.Teacher.Teacher
import sch.services.ScheduleTrait
import sct.utils.JsonSupport

class HttpRoutes(dbProvider: ScheduleTrait)(implicit val actorMaterializer: ActorMaterializer) extends JsonSupport {

  val routes: Route = {
    pathPrefix("api") {
      path("teacher") {
        post {
          entity(as[Teacher]) { teacher =>
            dbProvider.addTeacher(teacher)
            complete("OK")
          }
        }
      } ~ path("course") {
        post {
          entity(as[Course]) { course =>
            dbProvider.addCourse(course)
            complete("OK")
          }
        }
      } ~ path("class") {
        post {
          entity(as[sch.domain.Class.Class]) { classPayload =>
            dbProvider.addClass(classPayload)
            complete("OK")
          }
        }
      }
    }
  }
}
