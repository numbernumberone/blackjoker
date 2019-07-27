package utils

import java.net.URI

import anorm.{RowParser, SqlParser}
import java.sql.{Connection, DriverManager}

import anorm.{RowParser, SqlParser}
import com.typesafe.config.ConfigFactory
import play.api.Logger

/**
  * Created by gaoqihua on 9/11/17.
  */
object DBUtils {

  case class ConnInfo(host: String, port: Int, db: String, user: String, pass: String)

  def getConnInfo(env: String): ConnInfo = {
    val url = ConfigFactory.load().getString(s"url.$env")
    Logger.debug("url:" + url)
    val cleanURI: String = url.substring(5)
    val uri: URI = URI.create(cleanURI)
    val query = uri.getQuery
    val pairs = query.split("&|=").grouped(2)
    val param_map = pairs.map { case Array(k, v) => k -> v }.toMap
    Logger.debug("host:" + uri.getHost)
    Logger.debug("port:" + uri.getPort)
    Logger.debug("db:" + uri.getPath.substring(1))
    Logger.debug("user:" + param_map("user"))
    Logger.debug("pass:" + param_map("password"))
    ConnInfo(uri.getHost, uri.getPort, uri.getPath.substring(1), param_map("user"), param_map("password"))
  }

  def genericParser = SqlParser.folder(Map.empty[String, Any]) { (map, value, meta) =>
    Right(map + (meta.column.alias.get -> value))
  }

  def getConn(name: String): Connection = {
    val myDriver = new org.postgresql.Driver()
    DriverManager.registerDriver(myDriver)
    DriverManager.getConnection(ConfigFactory.load().getString("url." + name))
  }
}
