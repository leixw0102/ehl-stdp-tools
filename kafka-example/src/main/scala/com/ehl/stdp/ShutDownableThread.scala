package com.ehl.stdp

/**
  * Created by 雷晓武 on 2017/6/26.
  */
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicBoolean


abstract class ShutdownableThread(val name: String, val isInterruptible: Boolean = true)
  extends Thread(name) {
  this.setDaemon(false)
//  this.logIdent = "[" + name + "]: "
  val isRunning: AtomicBoolean = new AtomicBoolean(true)
  private val shutdownLatch = new CountDownLatch(1)

  def shutdown(): Unit = {
    initiateShutdown()
    awaitShutdown()
  }

  def initiateShutdown(): Boolean = {
    if (isRunning.compareAndSet(true, false)) {
      println("Shutting down")
      if (isInterruptible)
        interrupt()
      true
    } else
      false
  }

  /**
    * After calling initiateShutdown(), use this API to wait until the shutdown is complete
    */
  def awaitShutdown(): Unit = {
    shutdownLatch.await()
    println("Shutdown completed")
  }

  /**
    * This method is repeatedly invoked until the thread shuts down or this method throws an exception
    */
  def doWork(): Unit

  override def run(): Unit = {
    println("Starting")
    try {
      while (isRunning.get)
        doWork()
    } catch {
      case e: Exception =>
        isRunning.set(false)
        shutdownLatch.countDown()
        println("Stopped")
        System.exit(1);
      case e: Throwable =>
        if (isRunning.get())
          println("Error due to", e)
    }
    shutdownLatch.countDown()
    println("Stopped")
  }
}

