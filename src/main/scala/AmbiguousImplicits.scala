import scala.language.higherKinds

object AmbiguousImplicits {
  trait B[T]
  object B extends {
    implicit def d[T1: B, T2: B]: B[(T1, T2)] = ???
  }

  object G {
    def H[T : B, V : B] = {
      implicitly[B[((V, T), T)]]
    }
  }
}
