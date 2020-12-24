object KindMistake {
  trait A[B[D[X, Y <: X]]]
  trait C[E[T, S]]
  trait P[U, V <: U]
//  type T = A[C]
}
