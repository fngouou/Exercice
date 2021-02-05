package com.particeep.test

object WhatsWrong1 extends App {

  trait Interface {
    val city: String
    val support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {
    override val city = "Paris"
  }

  // This will print Paris
  print(Supporter.city) //What does this print ?
  // This will print "Ici c'est null"
  /* It's one of quirks of using val with trait
  * some Developper says it becaus e city value have not the time to be executed
  * the best pratice is to use defs or lazy val for variable city in interface*/
  print(Supporter.support) //What does this print and why ? How to fix it ?
}