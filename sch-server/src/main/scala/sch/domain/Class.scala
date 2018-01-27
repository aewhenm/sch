package sch.domain

object Class {

  case class Class(
                    teacherId: String,
                    courseId: String,
                    hours: Int,
                    groupId: String)
}
