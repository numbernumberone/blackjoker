package utils

import java.util
import java.util.Calendar

import com.typesafe.config.{Config, ConfigFactory}
import play.api.Logger

import scala.collection.JavaConverters._

/**
  * Created by gaoqihua on 6/20/17.
  */
object DateUtils {
  def zeroTime(myCal: Calendar) = {
    myCal.set(Calendar.HOUR, 0);
    myCal.set(Calendar.MINUTE, 0);
    myCal.set(Calendar.SECOND, 0);
  }
}
