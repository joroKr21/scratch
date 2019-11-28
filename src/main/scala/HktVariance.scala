object HktVariance {
  case class Holder[F[+_]](v: F[Int]) {
//    def asWiden[F2[+x] >: F[x]]: F2[Int] = v
  }
}
