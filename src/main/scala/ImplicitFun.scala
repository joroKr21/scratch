object ImplicitFun extends App {
  trait Covariants
  trait Invariants extends Covariants
  trait Implicits
  class Covariant[+T](override val toString: String) extends Invariants with Implicits

  class Invariant[T](x: String) extends Covariant[T](x)


  object Implicits {
    implicit val int: Invariant[Int] = new Invariant[Int]("int")
    implicit val short: Invariant[Short] = new Invariant[Short]("short")
  }

  object Covariants {
//    implicit def covariant[T :Covariant] :Covariant[Option[T]] = new Covariant("cov")
  }

  object Invariants {
    implicit def invariant[T :Invariant] :Invariant[Option[T]] = new Invariant("inv")
  }

//  println(implicitly[Covariant[Option[Int]]])
}
