package sch.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import sch.domain.Course.Course
import sch.domain.Teacher.Teacher
import sch.services.ScheduleTrait
import sch.utils.JsonSupport

class HttpRoutes(dbProvider: ScheduleTrait)(implicit val actorMaterializer: ActorMaterializer) extends JsonSupport {

  val routes: Route = {
    pathPrefix("api") {
      path("healthcheck") {
        get {
          complete("OK")
        }
      } ~ path("teacher") {
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
        } ~ parameters('course_id) { courseId =>
          complete(dbProvider.getCourseById(courseId))
        } ~ get {
          complete(dbProvider.getAllCourses)
        }
      } ~ path("class") {
        post {
          entity(as[sch.domain.Class.Class]) { classPayload =>
            dbProvider.addClass(classPayload)
            complete("OK")
          }
        }
      } ~ path("schedule") {
        parameters('weekDay) { weekDay =>
          val availableClasses = dbProvider.getClassesByDay(weekDay.toInt)
          complete(availableClasses)
        } ~ get {
          complete(dbProvider.getAllClasses)
        }
      } ~ path("teacher") {
        parameters('teacher_id) { teacherId =>
          complete(dbProvider.getTeacherById(teacherId))
        } ~ get {
          complete(dbProvider.getAllTeachers)
        }
      } ~ path("room") {
        parameters('room_id) { roomId =>
          complete(dbProvider.getRoomById(roomId))
        } ~ get {
          complete(dbProvider.getAllRooms)
        }
      } ~ path("group") {
        parameters('group_id) { groupId =>
          complete(dbProvider.getGroupById(groupId))
        } ~ get {
          complete(dbProvider.getAllGroups)
        }
      }
    }
  }
}
