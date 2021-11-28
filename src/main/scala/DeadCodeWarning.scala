object DeadCodeWarning {
  sealed trait HList {
    def ::[H](head: H): H :: this.type = new ::(head, this)
  }

  case object HNil extends HList
  case class ::[H, T <: HList](head: H, tail: T) extends HList

  sealed trait Foo {
    type Result <: HList
  }

  object Foo {
    type Aux[R <: HList] = Foo { type Result = R }
  }

  final case class FooSingle[H](head: H) extends Foo {
    type Result = H :: HNil.type
  }

  final case class FooCons[H, TL <: HList](head: H, tail: Foo.Aux[TL]) extends Foo {
    type Result = H :: TL
  }

  def run[R <: HList](foo: Foo.Aux[R]): R = foo match {
    case x: FooSingle[_] => x.head :: HNil
    case x: FooCons[_, _] => x.head :: run(x.tail)
  }

  run(FooCons(1, FooSingle("abc")))
}
