package com.ehl.stdp

import java.util.concurrent.{ArrayBlockingQueue, ConcurrentLinkedQueue}

import org.apache.kafka.clients.producer.KafkaProducer

import scala.util.Random

/**
 * @author ${user.name}
 */
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    println("concat arguments = " + foo(args))
//    val producer = new KafkaProducer[Integer,String](props);
//    val magicNum = new Random();
//
//    val content = "org.apache.kafka.common.serialization.StringSerializer,org.apache.kafka.common.serialization.IntegerSerializer,10.2.111.108:9092,10.2.111.110:9092,10.2.111.113:9092,{'code':0,'data':[],'success':true,'timestamp':1485163260884}".split(",")
//    val size = content.length
//    println(size)
//    val randomText = new Random()
//    var i=0;
//    while (i<30){
//      println(magicNum.nextInt()+"\t"+content(Math.abs(randomText.nextInt()%size)))
//      if(i>30){
//        return
//      }
//      i = i+1
//    }
val msgQueue = new ArrayBlockingQueue[String](10^4);
    val x = msgQueue.poll()
    println(x)

  }

}
