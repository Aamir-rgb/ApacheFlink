package part1recap

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

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

  //options and try
  val anOption:Option[Int] = Option(/*Something that might be null*/43)
  val doubleOption = anOption.map(_*2)

  val   anAttempt: Try[Int] = Try(12)
  val modifiedAttempt  = anAttempt.map(_*10)

  //Pattern Matching
  val anUnknown:Any = 45
  val medal = anUnknown match {
    case 1 => "gold"
    case 2 => "silver"
    case 2 => "bronze"
    case _=> "no medal"
  }
  val optionDescription = anOption match {
    case  Some(value)   => s"the option is not empty $value"
    case None => "hte option is empty"
  }

  //Futures
  implicit val ec: ExecutionContext  = ExecutionContext.fromExecutorService( Executors.newFixedThreadPool(8))
  val aFuture = Future(/*Something to be evaluated on another thread*/1+999)

  aFuture.onComplete {
    case Success(value) => println(s"the async value is $value")
    case Failure(exception) => println(s"the meaning of value failed: $exception")
  }
 val aPartialFunction : PartialFunction[Try[Int],Unit] = {
   case Success(value) => println(s"the async value is $value")
   case Failure(exception) => println(s"the meaning of value failed: $exception")
 }

  //map,flatMap,filter
  val doubledAsyncMOL: Future[Int] = aFuture.map(_*2)

  //implicits
  //1 implicit arguments and value
  implicit val timout:Int = 1000
  def setTimeout(f: () => Unit)(implicit tout:Int) = {
    Thread.sleep(tout)
  }


  //2 extension methods
  implicit class MyRichInt(number:Int) {
    def isEven : Boolean = number %2 == 0
  }

  setTimeout(() => println("timeout"))

  val is2Even = 2.isEven

  //3 conversion
  implicit def string2person(name:String):Person =
    Person(name,57)

  val daniel:Person = "Daniel"
  def main(args: Array[String]): Unit = {

  }
}
