package part2datastreams

import org.apache.flink.api.common.functions.{FlatMapFunction, MapFunction, ReduceFunction}
import org.apache.flink.api.common.serialization.SimpleStringEncoder
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector

object EssentialStreams {

 def applicationTemplate():Unit = {
   //Execution Environment
   val env:StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

   //Checking Parallelism
   println(env.getParallelism)

  //In Between Add Any sort Of Computations
   val simpleNumberStream :DataStream[Int] = env.fromElements(1,2,3,4)

   //perform some actions
   simpleNumberStream.print()



   //At the end
   env.execute()
 }

  //Transformations
  def demoTramsformations() : Unit = {
    val env:StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val numbers:DataStream[Int] = env.fromElements(1,2,3,4,5)
    //map
    val doubleNumbers:DataStream[Int] = numbers.map(_*2)

    //flat map
    val expandedNumbers:DataStream[Int] = numbers.flatMap(n => List(n,n+1))

    //filter
    val filteredNumbers:DataStream[Int] = numbers.filter(_%2 == 0)

    expandedNumbers.writeAsText("output/expandedStreams.txt")
    env.execute()

    //Exercises
    //1

  }
   case class  FizzBuzzResult(n:Long,output:String)
  def fizzBuzzExercise(): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val numbers = env.fromSequence(1,100)

    //map
    val fizzbuzz = numbers.map{ n =>
      val output =
      if(n%3== 0 && n%5 == 0) "fizzbuzz"
      else if (n%3 == 0) "fizz"
      else if(n%5 == 0) "buzz"
      else s"$n"
      FizzBuzzResult(n, output)
    }
      .filter(_.output =="fizzbuzz")
      .map(_.n)

    fizzbuzz.writeAsText("output/fizzbuzz").setParallelism(1)
    env.execute()

  }
  def main(args: Array[String]): Unit = {
    applicationTemplate()
    fizzBuzzExercise()
  }
}
