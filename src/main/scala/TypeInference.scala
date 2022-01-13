object TypeInference {
  trait MyTypeclass[F[_]]
  def f[F[_]: MyTypeclass, U](t:F[U]) = ???

  type MyType[T] = String
  implicit val impl:MyTypeclass[MyType] = ???

  val stream:Option[MyType[Int]] = ???
  for {
    keyStream <- stream
//    x = 17 // commenting this line makes it compile
  } yield f(keyStream)
}
