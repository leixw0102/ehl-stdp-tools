package com.ehl.stdp

import com.ehl.stdp.product.Producter
import com.ehl.stdp.queue.{Client, MsgQueue}

/**
  * Created by 雷晓武 on 2017/6/26.
  */
object SimpleKafka extends App{
  new Client().start()
  new Thread(new Producter("leixw-test-2")).start()

//print(MsgQueue.getMsg())

  Thread.sleep(60*60*1000L)


}
