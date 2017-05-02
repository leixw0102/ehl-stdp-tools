

/**
 * @author ehl
 */
package com.ehl.stdp

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

/**
  *
 *
 * @author ehl
 */
trait SparkOp{


  /**
    * 获取spark app name
    *
    * @return
    */
  def getSparkAppName:String

  /**
    * 設置hadoop配置
    */
  def setHadoopConfig(sc:SparkSession):Unit

  def initEhlConfig:EhlConfiguration


  val ehlConf=initEhlConfig
  /**
    * sparkContext
    *(op:(SparkContext)=>Unit)
    */
  def operateSpark(args:Array[String],ehlConf:EhlConfiguration)(op:SparkSession=>Unit)

}