object HktErasure {
  trait A[B[_]]
  class B1(x: String, y: A[List])
  class B2(x: String)(y: A[List])
}
