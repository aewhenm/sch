package sch.utils

import sch.domain.Course.Course
import sch.domain.Group.Group
import sch.domain.Room.Room
import sch.domain.Teacher.Teacher

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Seeder {

  def readClasses(): List[sch.domain.Class.Class] = {
    val bufferedClasses = io.Source.fromFile("/home/artur/Desktop/sch/sch-server/src/main/resources/class.txt")
    var list: ListBuffer[sch.domain.Class.Class] = ListBuffer()
    for (x <- bufferedClasses.getLines()) {
      val s = x.split(";")
      list += sch.domain.Class.Class(s(1), s(0), Random.nextInt(3) + 1, s(2))
    }
    bufferedClasses.close()
    list.toList
  }

  def readRooms(): List[Room] = {
    val bufferedRooms = io.Source.fromFile("/home/artur/Desktop/sch/sch-server/src/main/resources/room.txt")
    var list: ListBuffer[Room] = ListBuffer()
    for (x <- bufferedRooms.getLines()) {
      val s = x.split(";")
      list += Room(s(0), s(1).toInt, s(2), s(3))
    }
    bufferedRooms.close()
    list.toList
  }

  def readTeachers(): List[Teacher] = {
    val bufferedTeachers = io.Source.fromFile("/home/artur/Desktop/sch/sch-server/src/main/resources/teachers.txt")
    var list: ListBuffer[Teacher] = ListBuffer()
    for (x <- bufferedTeachers.getLines()) {
      val s = x.split(";")
      list += Teacher(s(0), s(1))
    }
    bufferedTeachers.close()
    list.toList
  }


  def readStudents(): List[Group] = {
    val bufferedStudents = io.Source.fromFile("/home/artur/Desktop/sch/sch-server/src/main/resources/student_group.txt")
    var list: ListBuffer[Group] = ListBuffer()
    for (x <- bufferedStudents.getLines()) {
      val s = x.split(";")
      list += Group(s(0), s(1).toInt)
    }
    bufferedStudents.close()
    list.toList
  }


  def readCourses(): List[Course] = {
    val bufferedCourses = io.Source.fromFile("/home/artur/Desktop/sch/sch-server/src/main/resources/course.txt")
    var list: ListBuffer[Course] = ListBuffer()
    for (x <- bufferedCourses.getLines()) {
      val s = x.split(";")
      list += Course(s(0), s(1))
    }
    bufferedCourses.close()
    list.toList
  }
}
