package sch.domain

class Schedule() {
  var timeSlots: List[List[sch.domain.Class.Class]] = List()
  var scheduleHash: Map[sch.domain.Class.Class, Int] = Map()
}
