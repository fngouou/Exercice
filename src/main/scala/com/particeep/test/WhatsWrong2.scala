package com.particeep.test

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai")
  )

  def byId(id: String): Future[Option[CEO]] = Future { ceos.find(_.id == id) }
}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2")
  )

  def byId(id: String): Future[Option[Enterprise]] = Future { enterprises.find(_.id == id) }
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future { enterprises.find(_.ceo_id == ceo_id) }
}

object WhatsWrong2 {

  //Review this code. What could be done better ? How would you do it ?

  /* Error in line order :
  *   - .get on Option can throw exception if Option is empty so we have to change the argument type too
  *   So to solve it i will change parameter by String
  *
  *  - Créer les Futures en amont plutot que dans le for pour éviter les problème d'attentes*/
  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    for {
      ceo <- CEODao.byId(ceo_id.get)
      enterprise <- EnterpriseDao.byCEOId(ceo_id.get)
    } yield {
      (ceo, enterprise)
    }
  }

  // Solving


  def getCEOAndEnterprise(ceo_id: String): Future[(Option[CEO], Option[Enterprise])] = {
    val ceoFuture = CEODao.byId(ceo_id)
    val enterpriseFuture = EnterpriseDao.byCEOId(ceo_id)
    for {
      ceo <- ceoFuture
      enterprise <- enterpriseFuture
    } yield {
      (ceo, enterprise)
    }
  }

}