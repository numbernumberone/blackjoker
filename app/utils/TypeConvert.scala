package utils

import java.sql.{Connection, ResultSet}

import org.json4s.{DefaultFormats, NoTypeHints}
import org.json4s.jackson.{Json, Serialization}
import play.api.db.Database

import scala.collection.mutable

/**
  * Created by gaoqihua on 5/26/17.
  */
object TypeConvert {
  implicit def intToString(x:Int) = x.toString

  implicit def stringToInt(s: String): Int = {
    try {
      java.lang.Integer.parseInt(s)
    } catch {
      case e: Exception => 0
    }
  }

  implicit def anyToInt(s: Any): Int = {
    try {
      Integer.parseInt(s.toString)
    } catch {
      case e: Exception => 0
    }
  }

  implicit  def any2Double(s: Any): Double = {
    try {
      java.lang.Double.parseDouble(s.toString)
    } catch {
      case e: Exception => 0.0
    }
  }

  implicit  def any2String(s: Any): String = {
    try {
    s.toString
    } catch {
      case e: Exception => ""
    }
  }

  implicit def toSqlDate(d: java.util.Date): java.sql.Date = {
    try {
      new java.sql.Date(d.getTime)
    } catch {
      case e: Exception => null
    }
  }

  implicit def rs2Array(rs: ResultSet):List[mutable.HashMap[String, Any]] = {
    val columns = rs.getMetaData.getColumnCount
    val rsArray = new mutable.MutableList[mutable.HashMap[String, Any]]
    while (rs.next) {
      val map = new mutable.HashMap[String, Any]()
      for (i <-1 to columns) {
        map.put(rs.getMetaData.getColumnName(i), rs.getObject(i))
      }
      rsArray.+=(map)
    }
    rsArray.toList
  }

  implicit def toJsonStr(obj:AnyRef):String = {
    implicit val formats = Serialization.formats(NoTypeHints)
    Json(DefaultFormats).write(obj)
  }
}
