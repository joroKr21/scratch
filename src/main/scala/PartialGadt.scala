object PartialGadt {
  sealed abstract class Foo[F[_], A]
  case class Bar[F[_]](fa: F[Int]) extends Foo[F, Unit]
  class Fun[A, B]

//  def f[A, B](foo: Foo[({ type λ[b] = Fun[A, b] })#λ, B]): Unit = foo match {
//    case Bar(_) => ()
//  }
}
