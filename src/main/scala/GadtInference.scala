object GadtInference {
//  def f[A, E](l1: L1[A, E]) = l1 match {
//    case L1L(L2Cons(L2Nil()), l3) =>
//      L1L3(L3Cons(L1L3(l3))) // removing asInstanceOf here causes compile error
//    case _ => ???
//  }

  sealed trait L1[+A, +E]
  case class L1L3[A, E](l3: L3[A, E]) extends L1[A, E]
  case class L1L[A, Z, E](l2: L2[A, Z], l3: L3[Z, E]) extends L1[A, E]

  sealed trait L2[+A, +E]
  case class L2Nil[A]() extends L2[A, A]
  case class L2Cons[A, E](tail: L2[List[A], E]) extends L2[A, E]

  sealed trait L3[+A, +E]
  case class L3Cons[A, E](tail: L1[List[A], E]) extends L3[A, E]

  def l3[A, E]: L3[A, E] = ???
  L1L3(L3Cons(L1L3(l3)))
}
