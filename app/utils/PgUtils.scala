package utils

import java.sql.{Connection, DriverManager}


object PgUtils {
  val pgDbMap = Map(
    "hzlw" -> "jdbc:postgresql://rm-bp1e781z8v3hvp061o.pg.rds.aliyuncs.com:2345/hzlw?user=hzlw&password=bHxMFRhp9lwOqCSo",
    "travel" -> "jdbc:postgresql://118.31.59.12:65432/travel?user=cdl&password=BA2aE4c97D3BE0Do"
  )

  val defPgDB = "travel"

  def getConn(env: String = "hzlw"): Connection = {
    val myDriver = new org.postgresql.Driver()
    DriverManager.registerDriver(myDriver)
    DriverManager.getConnection(pgDbMap(env))
  }

}

