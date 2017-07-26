package com.ehl.stdp.queue

import java.util.concurrent.{ArrayBlockingQueue, ConcurrentLinkedQueue, LinkedBlockingQueue, TimeUnit}

import scala.util.Random

/**
  * Created by 雷晓武 on 2017/5/15.
  */
object MsgQueue {
  val msgQueue = new ArrayBlockingQueue[String](10^5);

  /**
    *
    * @param msg
    */
  def putMsg(msg:String)=msgQueue.put(msg);

  def getMsg():String=msgQueue.take()


}

class Client extends Thread{
  val content = "org.apache.kafka.common.serialization.StringSerializer,org.apache.kafka.common.serialization.IntegerSerializer,10.2.111.108:9092,10.2.111.110:9092,10.2.111.113:9092,{'code':0,'data':[],'success':true,'timestamp':1485163260884}".split(",")

  val size = content.length

  val randomText = new Random()

  override def run:Unit={
    generated()
  }

  def generated() {

  while (true) {
    Thread.sleep(1)
    MsgQueue.putMsg(content(Math.abs(randomText.nextInt() % size)))
  }
}

}
