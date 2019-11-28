import scala.language.higherKinds

object TypeProjAlias {
  trait One[A] {
    type Two[B <: A] >: Altogether[A, B] <: Altogether[A, B]
  }

  trait Altogether[A, B <: A]
  type Test = One[Int]#Two[Int]
}
