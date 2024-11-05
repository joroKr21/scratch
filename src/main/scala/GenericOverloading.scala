object GenericOverloading {
  object A {
    def a[F](x: Int) = 0
    def a[F](x: String) = 0
  }
//  A.a[Int][String](0)
}
