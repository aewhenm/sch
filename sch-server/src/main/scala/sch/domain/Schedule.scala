package sch.domain

import sch.utils.Constants

import scala.collection.mutable.ListBuffer
import scala.util.Random

class Schedule() {

  var timeSlots: ListBuffer[ListBuffer[sch.domain.Class.Class]] = ListBuffer.fill(Constants.NUMBER_OF_ROOMS *
                                                                                  Constants.WORK_DAYS *
                                                                                  Constants.AMOUNT_OF_HOURS)(ListBuffer())
  var scheduleHash: Map[sch.domain.Class.Class, Int] = Map()

  def addClass(newClass: sch.domain.Class.Class): Unit = {

    val timeAndPlace = Random.nextInt(timeSlots.size)
    timeSlots(timeAndPlace) += newClass
    scheduleHash += (newClass -> timeAndPlace)
  }

  def mutate(anotherSchedule: Schedule): Schedule = {

    val schedule = new Schedule()

    for ((classReference, i) <- scheduleHash) {

      // Random mutation genome selector
      if (Random.nextInt(12) + 1 > 6) {
        schedule.scheduleHash += (classReference -> i)
        schedule.timeSlots(i) += classReference
      }
    }

    for ((classReference, i) <- anotherSchedule.scheduleHash) {
      if (!schedule.scheduleHash.contains(classReference)) {
        schedule.scheduleHash += (classReference -> i)
        schedule.timeSlots(i) += classReference
      }
    }
    schedule
  }
}
