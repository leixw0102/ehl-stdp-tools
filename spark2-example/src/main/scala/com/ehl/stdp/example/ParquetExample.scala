package com.ehl.stdp.example

import com.ehl.stdp.{AbstractSparkEhl, EhlConfiguration}


/**
  * Created by 雷晓武 on 2017/4/12.
  */
object ParquetExample extends AbstractSparkEhl with App{
  override def getSparkAppName: String = ""

  override def initEhlConfig: EhlConfiguration = {
    null;
  }

  operateSpark(args,ehlConf )(session=>{
    session.sparkContext.textFile("")
  })
}
