package com.particeep.test

import scala.annotation.tailrec

object BasicScala extends App{


  def encoreParamsInUrl(params: Map[String,String]) : String ={
    var url : String = "sds";
    for((k,v) <- params){

     url = k match{
          case "sort_by" =>  url.concat("?").concat(k).concat("=").concat(v)
          case _ => url.concat("&").concat(k).concat("=").concat(v)
        }
    }
    url
  }

  // \w any character [a-zA-Z0-9] include _
  //Pour trouver l'ajout de \. je me suis aidé de regex101.com
  def isEmail(maybeEmail: String): Boolean = {
    """(\w+)@([\w\.]+)""".r.unapplySeq(maybeEmail).isDefined
  }

  println(isEmail("qsdxws@gmail.com"))


  def power(base: Int, exp: Int): BigInt = {
    @tailrec
    def power_tail(result: BigInt, incr: Int): BigInt = incr match {
      case 0 => 1
      case _ => power_tail(result*base, incr-1)
    }
    power_tail(base, exp)
  }
  println(power(2, 39))
}
