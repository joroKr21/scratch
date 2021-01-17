object HKTSpecial {
  import scala.{specialized => sp}

  trait TC[M[_], @sp(Int) A]

  object Compiles {
    def f2[M[_], A](implicit ev: TC[M, A]): M[A] = ???
    def f1[M[_], A](implicit ev: TC[M, A]): M[A] = f2[M, A](ev)
  }

  object CompilesNot {
    def f2[M[_], @sp(Int) A](implicit ev: TC[M, A]): M[A] = ???
//    def f1[M[_], @sp(Int) A](implicit ev: TC[M, A]): M[A] = f2[M, A](ev)
  }
}
