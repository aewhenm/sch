package sch.domain

object Class {

  case class Class(courseId: String, hours: String, classType: String, students: List[String])
}
