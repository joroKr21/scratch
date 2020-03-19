object Wants {

  implicit val nope = new AnyRef with Wanted

  def main(args: Array[String]) {
    import Wanted._
    println(nope eq implicitly[Wanted])
    println(Wanted.Default eq implicitly[Wanted])
    println(nope eq implicitly[Wanted])
  }
}

trait Wanted

object Wanted {
  implicit object Default extends Wanted
}