package sch.domain

object Teacher {

  case class Teacher(
                      name: String,
                      surname: String,
                      id: String,
                      classes: List[String])
}
