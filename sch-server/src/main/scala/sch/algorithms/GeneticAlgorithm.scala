package sch.algorithms

import sch.domain.Schedule
import sch.utils.Seeder

object GeneticAlgorithm {

  // TODO: Read data from Database
  def readClasses(): Unit = {
    val seedData = Seeder.readClasses()
    val schedule = new Schedule()
    seedData.foreach(x => schedule.addClass(x))
  }
}
