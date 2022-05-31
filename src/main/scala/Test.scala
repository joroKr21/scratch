object Test {
  trait T[A]
  @main def run(): Unit =
    println(ShowTypeLambda.show[T])
}
