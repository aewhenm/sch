package sch.domain

import sch.utils.Constants

import scala.collection.mutable.ListBuffer
import scala.util.Random

class Schedule() {

  var timeSlots: ListBuffer[ListBuffer[sch.domain.Class.ClassResponse]] = ListBuffer.fill(Constants.NUMBER_OF_ROOMS *
                                                                                  Constants.WORK_DAYS *
                                                                                  Constants.AMOUNT_OF_HOURS)(ListBuffer())
  var scheduleHash: Map[sch.domain.Class.ClassResponse, Int] = Map()

  def addClass(newClass: sch.domain.Class.Class): Unit = {

    val timeAndPlace = Random.nextInt(timeSlots.size)

    val roomId = ((timeAndPlace % (Constants.AMOUNT_OF_HOURS * Constants.NUMBER_OF_ROOMS)) / 12).toString
    val startTime = timeAndPlace % 11

    val classResponse = sch.domain.Class.ClassResponse(
                                            newClass.teacherId,
                                            newClass.courseId,
                                            newClass.hours,
                                            newClass.groupId,
                                            newClass.equiped,
                                            roomId,
                                            startTime.toString)

    for (time <- 0 until classResponse.hours) {
      if (startTime + 9 + time < 21)
        timeSlots(timeAndPlace + time) += classResponse
    }

    scheduleHash += (classResponse -> timeAndPlace)
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

  def calculateFitnessRate(): Float = {
    var totalScore: Int = 0

  }
}
