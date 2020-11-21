object InfiniteLoop {
  trait Trait {
    type T <: Trait
    def T :T = ???
  }

  trait Link[+L <: Trait] extends Trait {
    val link :L
    type T <: Link[link.T]
  }

  class Implicit[T <: Trait]

  implicit def Implicit = new Implicit[Trait]
  implicit def ImplicitLink[L <: Trait] = new Implicit[Link[L]]

  val t = new Link[Trait] { val link = this }

  def subTrait[T <: Trait](t :T)(implicit typeClass :Implicit[T]) = ???

//  subTrait(t.T)
}
