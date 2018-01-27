package sch.routes

import akka.stream.ActorMaterializer
import akka.actor.{ActorRef, ActorRefFactory}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import sch.domain.Course.Course
import sch.domain.Teacher.Teacher
import sct.utils.JsonSupport
import spray.json._

class HttpRoutes()(implicit val actorMaterializer: ActorMaterializer) extends JsonSupport {

  val routes: Route = {
    pathPrefix("api") {
      path("teacher") {
        post {
          entity(as[Teacher]) { teacher =>
            println(teacher.id)
            println(teacher.name)
            println(teacher.surname)
            complete("OK")
          }
        }
      } ~ path("course") {
        post {
          entity(as[Course]) { course =>
            println(course.courseId)
            println(course.hours)
            println(course.students)
            println(course.subject)
            println(course.teacherId)
            complete("OK")
          }
        }
      } ~ path("class") {
        post {
          entity(as[sch.domain.Class.Class]) { classPayload =>
            println(classPayload.courseId)
            println(classPayload.hours)
            println(classPayload.students)
            complete("OK")
          }
        }
      }
    }
  }

}
