object AbstractLub {
  type F[+A] <: A
  def mark[A](x: A): F[A] = ???

  class P
  class T[+x] extends P

//  (
//    if (???) ??? : T[Nothing]
//    else mark(??? : T[String])
//  ): T[_]
  /* found   : Tst.P
     required: Tst.T[_] */
}
