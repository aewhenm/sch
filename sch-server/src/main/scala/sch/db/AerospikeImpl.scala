package sch.db

import com.aerospike.client.{AerospikeClient, Bin, Key, Value}
import sch.domain.{Class, Course, Teacher}
import sch.services.ScheduleTrait
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

class AerospikeImpl extends ScheduleTrait {

  private val interface = "localhost"
  private val port = 3000

  val client = new AerospikeClient(interface, port)

  override def addTeacher(teacher: Teacher.Teacher): Unit = {

    val key = new Key("test", "teacher", teacher.id)
    val name = new Bin("name", Value.get(teacher.name))
    val surname = new Bin("surname", Value.get(teacher.surname))
    val id = new Bin("id", Value.get(teacher.id))

    client.put(null, key, name, surname, id)
  }

  override def addClass(schClass: Class.Class): Unit = {

    val key = new Key("test", "class", schClass.classId)
    val courseId = new Bin("course_id", Value.get(schClass.courseId))
    val classId = new Bin("class_id", Value.get(schClass.classId))
    val students = new Bin("students", Value.get(bufferAsJavaList(ListBuffer(schClass.students: _*))))
    val classType = new Bin("class_type", Value.get(schClass.classType))
    val hours = new Bin("hours", Value.get(schClass.hours))

    client.put(null, key, courseId, classId, students, classType, hours)
  }

  override def addCourse(course: Course.Course): Unit = {
    println("Course added")
  }
}
