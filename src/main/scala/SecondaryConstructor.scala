object SecondaryConstructor extends App {
  import scala.reflect.runtime.universe._
  import scala.reflect.runtime.currentMirror
  import scala.tools.reflect.ToolBox

  class A(i: Int) { def this() { this(0) } } // ok
  val tb = currentMirror.mkToolBox()
  val a = tb.typecheck(q"class A(i: Int) { def this() { this(0) } }")
  println(a)
}
