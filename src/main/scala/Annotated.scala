import foo.InterfaceAudience
import scala.reflect.runtime.universe._

object Annotated extends App {
  @InterfaceAudience.Public class Foo
  println(typeOf[Foo])
}
