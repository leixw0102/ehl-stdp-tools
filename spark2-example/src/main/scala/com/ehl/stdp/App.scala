package com.ehl.stdp

import java.lang.management.ManagementFactory

/**
 * @author ${user.name}
 */
object App {
  
  def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {

    val t = "sdfd"
    println(t.stripSuffix("d"))
    val m=ManagementFactory.getRuntimeMXBean()
    println(m.getName()+"\t"+m.getVmName)
    println("concat arguments = " + foo(args))
  }

}
