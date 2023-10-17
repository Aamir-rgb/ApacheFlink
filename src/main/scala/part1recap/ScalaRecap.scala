package part1recap

import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Try, Success, Failure}

object ScalaRecap {

  //value
  val aBoolean:Boolean = false
  var aVariable:Int = 56
  aVariable+=1

  //expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  //instructions vs expressions
  //Unit = void
 val thUnit:Unit = println("Hello Scala")


  //OOP
  class Animal
  class Cat extends Animal
  trait Carnivore {
    def eat(Animal:Animal):Unit
  }

  //Inheritance: extends <=1 but from >=0 traits
  class Crocodile extends Animal with Carnivore {
    override def eat(animal:Animal):Unit = println("Eating this poor fellow")
  }

  //Singleton Object
  object MySingleton

  //companions
  object Carnivore

  //Case Class
  case class Person(name:String,age:Int)

  //Generics
  class MyList[A]

  //Method Notation
  //croc.eat(animal) or croc eat animal
  val three = 1+2
  val three_v2 = 1.+(2)

  //FP
  val incrementer : Int => Int = x => x+1
  val incremented = incrementer(4) //same as incrementer.apply(4)

  //map,flatMap,filter
  val processedList = List(1,2,3).map(incrementer)
  val aLongerList = List(1,2,3).flatMap(x => List(x,x+1))

  //for comprehensions
  val checkerBoard = List(1,2,3).flatMap(n =>List('a','b','c').map(c =>(n,c)))
  val checkerBoard_v2 = for {
    n <- List(1,2,3)
    c <- List('a','b','c')
  }yield(n,c)
  def main(args: Array[String]): Unit = {

  }
}
