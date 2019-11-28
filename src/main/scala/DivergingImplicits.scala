object DivergingImplicits {

  object Test {
    def rule[I <: HList, O <: HList](r: (I, O)): (I, O) = ???

    val f: Ops3[HNil, Int, Int, Int] = null
    //f.apply[Int](identity): (HNil, Int :: Int :: Int :: HNil)
//    rule { f(identity) }
  }

  sealed trait HList
  final case class ::[+H, +T <: HList](head: H, tail: T) extends HList
  sealed trait HNil extends HList {
    def ::[H](h: H) = new ::(h, this)
  }
  case object HNil extends HNil

  sealed trait Ops3[II <: HList, A, B, C] {
    def apply[RR](f: C => RR)
      (implicit j: Join[II, HNil, A :: B :: HNil, RR]): (j.In, j.Out)
  }

  trait ReversePrepend[P <: HList, S <: HList] extends DepFn2[P, S] { type Out <: HList }

  trait LowPriorityReversePrepend {
    type Aux[P <: HList, S <: HList, Out0 <: HList] = ReversePrepend[P, S] { type Out = Out0 }
  }

  trait DepFn2[T, U] {
    type Out
    def apply(t: T, u: U): Out
  }

  object ReversePrepend extends LowPriorityReversePrepend {
    def apply[P <: HList, S <: HList](implicit prepend: ReversePrepend[P, S]): Aux[P, S, prepend.Out] = prepend

    implicit def hnilReversePrepend1[P <: HNil, S <: HList]: Aux[P, S, S] =
      new ReversePrepend[P, S] {
        type Out = S
        def apply(prefix: P, suffix: S) = suffix
      }

    implicit def hlistReversePrepend[PH, PT <: HList, S <: HList](implicit rpt: ReversePrepend[PT, PH :: S]): Aux[PH :: PT, S, rpt.Out] =
      new ReversePrepend[PH :: PT, S] {
        type Out = rpt.Out
        def apply(prefix: PH :: PT, suffix: S): Out = ???
      }
  }

  sealed trait Join[I <: HList, L1 <: HList, L2 <: HList, R] {
    type In <: HList
    type Out <: HList
  }
  object Join {
    implicit def join[I <: HList, L1 <: HList, L2 <: HList, R, In0 <: HList, Out0 <: HList](implicit x: Aux[I, L1, L2, R, HNil, In0, Out0]): Join[I, L1, L2, R] { type In = In0; type Out = Out0 } = ???

    sealed trait Aux[I <: HList, L1 <: HList, L2 <: HList, R, Acc <: HList, In <: HList, Out <: HList]
    object Aux extends Aux1 {
      // if R <: HList and L1 empty and L2 non-empty move head of L2 to Acc
      implicit def iter2[I <: HList, H, T <: HList, R <: HList, Acc <: HList, Out <: HList](implicit x: Aux[I, HNil, T, R, H :: Acc, I, Out]): Aux[I, HNil, H :: T, R, Acc, I, Out] = ???

      // if R <: HList and L1 and L2 empty set Out = reversePrepend Acc before R
      implicit def terminate[I <: HList, R <: HList, Acc <: HList, Out <: HList](implicit x: ReversePrepend.Aux[Acc, R, Out]): Aux[I, HNil, HNil, R, Acc, I, Out] = ???
    }
    abstract class Aux1 {
      // convert R to R :: HNil
      implicit def forAny[I <: HList, L1 <: HList, L2 <: HList, R, Acc <: HList, Out <: HList](implicit x: Aux[I, L1, L2, R :: HNil, Acc, I, Out]): Aux[I, L1, L2, R, Acc, I, Out] = ???
    }
  }
}
