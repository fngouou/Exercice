package com.particeep.test
import  scala.collection.mutable
/**
 * Tell developer names by the department code
 * Expected result:
 * Map(frontend -> List(Remy, Alexandre), analytics -> List(Pierre), api -> List(Noe))
 */
object MapYourMap extends App {

  val devNames = Map("dev1" -> "Pierre", "dev2" -> "Remy", "dev3" -> "Noe", "dev4" -> "Alexandre")
  val devDepartments = Map("dev1" -> "analytics", "dev2" -> "frontend", "dev3" -> "api", "dev4" -> "frontend")

  val namesInDepartments:Map[String, List[String]] = {
    val nid = for {
      (k, v) <- devDepartments.toList
      (a, b) <- devNames if k == a
    } yield (v, b)
   // var w = devDepartments.toList ::: devNames.toList;

    nid.groupBy(_._1).mapValues(_.map(_._2))

  }

  print(namesInDepartments)
}