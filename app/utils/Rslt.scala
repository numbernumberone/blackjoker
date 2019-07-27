package utils

/**
  * Created by gaoqihua on 7/26/17.
  */
case class Rslt(rc:Int, obj:Any)

object RsltCode {
  val OK = 0
  val NEED_PARAMS = 1
  val BAD_PARAMS = 2
  val CONSTRAINT_VIOLATION = 3
  val NOT_EXIST = 4
  val MSG_WAIT = 5
  val MSG_SEND = 6
  val SERVER_BUSY = 9999
}

object RsltUtils {
  def commonError():Rslt = {
    Rslt(RsltCode.SERVER_BUSY, "服务器有些繁忙,请稍后重试")
  }

  def ok(obj:Any):Rslt = {
    Rslt(RsltCode.OK, obj)
  }

  def waitBugMsgSend():Rslt = {
    Rslt(RsltCode.MSG_WAIT, "发现bug进入等待阶段")
  }

  def invokeBugMsgSend():Rslt = {
    Rslt(RsltCode.MSG_SEND, "确认bug并已发送给负责人")
  }
}
