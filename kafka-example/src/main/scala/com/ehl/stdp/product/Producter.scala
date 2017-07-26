package com.ehl.stdp.product

import java.util.Properties

import com.ehl.stdp.queue.MsgQueue
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import scala.util.Random

/**
  * Created by 雷晓武 on 2017/5/15.
  */
class Producter(topic:String) extends Runnable{

  val props = new Properties();
  props.put("bootstrap.servers", "10.2.111.108:9092,10.2.111.110:9092,10.2.111.113:9092");
  props.put("client.id", "DemoProducer");
  props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
  props.put("partitioner.class","com.ehl.stdp.EhlPartition")
   val producer = new KafkaProducer[Integer,String](props);
  val magicNum = new Random();


  override def run(): Unit = {
    while (true){
      val msg = MsgQueue.getMsg()
      producer.send(new ProducerRecord[Integer,String](topic,
        Math.abs(magicNum.nextInt()),
        msg)//).get()
//        println(metadata.toString)
        ,new DemoCallBack())
    }
  }
}

class DemoCallBack extends Callback {
  override def onCompletion(recordMetadata: RecordMetadata, e: Exception): Unit = {
    println(recordMetadata.topic() +"\t"+recordMetadata.partition()+"\t"+recordMetadata.offset())
  }
}

