package sch.db

import com.aerospike.client.AerospikeClient
import sch.domain.{Class, Course, Teacher}
import sch.services.ScheduleTrait

class AerospikeImpl extends ScheduleTrait {

  private val interface = "localhost"
  private val port = 3000

  val client = new AerospikeClient(interface, port)

  override def addTeacher(teacher: Teacher.Teacher): Unit = {
    println("Teacher added")
  }

  override def addClass(schClass: Class.Class): Unit = {
    println("Class added")
  }

  override def addCourse(course: Course.Course): Unit = {
    println("Course added")
  }
}
