package sch.domain

object Class {

  case class Class(
                    teacherId: String,
                    courseId: String,
                    hours: Int,
                    groupId: String,
                    equiped: Boolean)

  case class ClassResponse(
                          teacherId: String,
                          courseId: String,
                          hours: Int,
                          groupId: String,
                          isEquiped: Boolean,
                          roomId: String,
                          startHour: String)
}
