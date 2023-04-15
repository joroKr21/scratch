import scala.reflect.runtime.universe._

// scala/but#7538
object PathDiff extends App {
  println((typeOf[foo.Foo[_]] member TypeName("SubBar")).info.asInstanceOf[ClassInfoType].parents)
  println((typeOf[foo.JFoo[_]] member TypeName("$SubBar")).info.asInstanceOf[ClassInfoType].parents)
}
