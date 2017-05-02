package com.ehl.stdp.reflect

import scala.reflect.runtime.universe._
/**
  * Created by 雷晓武 on 2017/4/20.
  */


class Person(name: String, age: Int) {
  var hight: Double = 0.0
  def getName = name
}
object ReflectionUtils extends App{
  private val classLoader = Thread.currentThread().getContextClassLoader
  private val mirror = runtimeMirror(classLoader)

  val john = new Person("John", 23) {
    hight = 1.7
  }

//  val m1=mirror.staticClass(john)
//  val method = mirror.classSymbol(Person.getClass)//.decl(TermName("getName")).asMethod
//  println(method)

//  val r=m1.reflectClass(method)
//  r()

}
