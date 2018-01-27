package sch.utils

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import sch.domain.Class._
import sch.domain.Course._
import sch.domain.Group.Group
import sch.domain.Room.Room
import sch.domain.Teacher._
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val teacherFormat: RootJsonFormat[Teacher] = jsonFormat2(Teacher)
  implicit val courseFormat: RootJsonFormat[Course] = jsonFormat2(Course)
  implicit val classFormat: RootJsonFormat[Class] = jsonFormat5(Class)
  implicit val roomFormat: RootJsonFormat[Room] = jsonFormat4(Room)
  implicit val groupFormat: RootJsonFormat[Group] = jsonFormat2(Group)
  implicit val classResponseFormat: RootJsonFormat[ClassResponse] = jsonFormat7(ClassResponse)
}
