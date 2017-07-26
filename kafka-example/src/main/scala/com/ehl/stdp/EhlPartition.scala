package com.ehl.stdp

import java.util

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.{Cluster, PartitionInfo}

/**
  * Created by 雷晓武 on 2017/5/17.
  */
class EhlPartition extends Partitioner {
  override def close(): Unit = {}

  override def partition(topic: String, key: scala.Any, keyBytes: Array[Byte], value: scala.Any, valueBytes: Array[Byte], cluster: Cluster): Int = {
    // TODO Auto-generated method stub
//    println(s"key = $key value = ${value}")
    val partitions = cluster.partitionsForTopic(topic);
    val numPartitions = partitions.size();
    var partitionNum = 0;
    try {
      partitionNum = Integer.parseInt(key.toString);
    } catch  {
      case e:Exception=>partitionNum = key.hashCode() ;
    }
    return Math.abs(key.toString.hashCode  % numPartitions);
  }

  override def configure(configs: util.Map[String, _]): Unit = {}
}
