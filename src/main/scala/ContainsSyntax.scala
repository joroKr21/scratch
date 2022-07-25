import cats.syntax.all._

object ContainsSyntax {
  def main(args: Array[String]): Unit =
    println(List("a").map(List("a").contains_(_)))
}
