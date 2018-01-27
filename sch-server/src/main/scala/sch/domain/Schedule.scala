package sch.domain

import sch.domain.Class.ClassResponse
import sch.services.ScheduleTrait
import sch.utils.Constants

import scala.collection.mutable.ListBuffer
import scala.util.Random

class Schedule(dbProvider: ScheduleTrait) {

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

    val schedule = new Schedule(dbProvider)

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

    for ((classReference, i) <- scheduleHash) {

      var isClassesCollide = false
      for (p <- 0 until classReference.hours) {
        if (timeSlots(i + p).length > 1) isClassesCollide = true
      }
      if (!isClassesCollide) totalScore += 1

      val room = dbProvider.getRoomById(classReference.roomId)
      val group = dbProvider.getGroupById(classReference.groupId)
      if (room.seatsNumber.toInt >= group.size) totalScore += 1

      if (!classReference.isEquiped) totalScore += 1
      else if (classReference.isEquiped && room.isEquiped) totalScore += 1

      val concreteTeacher = dbProvider.getTeacherById(classReference.teacherId)
      val numberOfDay = i / (Constants.WORK_DAYS * Constants.AMOUNT_OF_HOURS)
      val startOfDay = i * Constants.WORK_DAYS * Constants.AMOUNT_OF_HOURS
      val endOfDay = startOfDay + Constants.WORK_DAYS * Constants.AMOUNT_OF_HOURS

      var visited: Map[ClassResponse, Int] = Map()

      var teacherOccuranceTime = 0
      var groupOccuranceTime = 0

      for (index <- startOfDay until endOfDay) {
        if (timeSlots(index).nonEmpty) {
          val slots = timeSlots(index).toList
          slots.foreach(x => {
            if (!visited.contains(x)) {
              if (concreteTeacher.id == x.teacherId) teacherOccuranceTime += 1
              if (group.id == x.groupId) groupOccuranceTime += 1
              visited += (x -> 1)
            }
          })
        }
      }

      if (teacherOccuranceTime == 1)  totalScore += 1
      if (groupOccuranceTime == 1) totalScore += 1
    }
    val maxScore: Float = scheduleHash.size * 5
    val fitness: Float = totalScore / maxScore
    fitness
  }
}
