package sch.domain

object Group {
  case class Group(
                    id: String,
                    size: Int,
                    classesId: List[String])
}
