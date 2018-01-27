package sch.domain

object Course {

  case class Course(
                     teacherId: String,
                     subject: String,
                     hours: String,
                     courseId: String,
                     students: List[String])
}
