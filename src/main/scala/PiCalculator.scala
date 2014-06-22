package com.tugo

import com.datatorrent.api.annotation.ApplicationAnnotation
import com.datatorrent.api.Context.OperatorContext
import com.datatorrent.api._
import java.util.Random
import org.apache.hadoop.conf.Configuration
;

class Point(val x : Int, val y: Int) {
  // Needed for kryo serialization.
  def this() = this(0,0)

  def dist() = x * x + y * y;

  override
  def toString = "[ " + x + ", " + y + " ]"
}

class RandomIntGenerator extends InputOperator {

  override def teardown(): Unit = {}

  override def setup(p1: OperatorContext): Unit = {}

  override def endWindow(): Unit = {}

  override def beginWindow(p1: Long): Unit = {}

  @transient
  val out : DefaultOutputPort[Point] = new DefaultOutputPort[Point]();


  var tupleBlast = 100
  var sleepTime = 10
  val r = new Random()

  override def emitTuples(): Unit = {
    for (i <- 1 to tupleBlast) {
      val x = r.nextInt(100)
      val y = r.nextInt(100)

      out.emit(new Point(x, y))
    }
    if (sleepTime != 0)
      Thread.sleep(sleepTime)
  }
}

class PiCalculator extends BaseOperator {
  val base = 100 * 100
  var inArea = 0
  var totalArea = 0

  @transient
  val in : DefaultInputPort[Point] = new DefaultInputPort[Point] {
    override def process(p: Point): Unit = {
      if (p.dist() < base)
        inArea = inArea + 1
      totalArea = totalArea + 1
    }
  }

  @transient
  val out : DefaultOutputPort[Double] = new DefaultOutputPort[Double]

  override def endWindow() {
    val result = (inArea.toDouble / totalArea.toDouble) * 4.0
    out.emit(result)
  }
}

class ConsoleOutOperator[T] extends BaseOperator {
  @transient
  val in : DefaultInputPort[T] = new DefaultInputPort[T] {
    override def process(obj: T): Unit = {
      println(obj)
    }
  }
}

@ApplicationAnnotation(name="PiCalculatorScala")
class TestDTApp extends StreamingApplication {

  override def populateDAG(dag: DAG, conf: Configuration): Unit = {
    val gen = dag.addOperator("gen", new RandomIntGenerator)
    val cal = dag.addOperator("cal", new PiCalculator)
    val out = dag.addOperator("out", new ConsoleOutOperator[Double])

    dag.addStream[Point]("data", gen.out, cal.in);
    dag.addStream[Double]("result", cal.out, out.in)
  }
}
