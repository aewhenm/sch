package sch.domain

object Room {
  case class Room(
                   id: String,
                   number: Int,
                   seatsNumber: String,
                   roomType: String)
}
