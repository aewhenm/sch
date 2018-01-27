package sch.domain

object Class {

  case class Class(
                    classId: String,
                    courseId: String,
                    hours: String,
                    classType: String,
                    students: List[String])
}
