package sch.services

import sch.domain.Course.Course
import sch.domain.Group.Group
import sch.domain.Room.Room
import sch.domain.Teacher.Teacher

trait ScheduleTrait {

  def addTeacher(teacher: Teacher): Unit
  def addClass(schClass: sch.domain.Class.Class): Unit
  def addCourse(course: Course): Unit
  def getClassesByDay(weekDay: Int): List[sch.domain.Class.ClassResponse]
  def getAllClasses: List[sch.domain.Class.ClassResponse]
  def getTeacherById(teacherId: String): Teacher
  def getAllTeachers: List[Teacher]
  def getGroupById(groupId: String): Group
  def getAllGroups: List[Group]
  def getRoomById(roomId: String): Room
  def getAllRooms: List[Room]
  def getCourseById(courseId: String): Course
  def getAllCourses: List[Course]
}
