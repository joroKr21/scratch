// scala/bug#12142
object HKApplication {
  trait Bounds {
    type Upper <: Bounds
  }

  trait Template[+X <: Bounds] extends Bounds { outer =>
    val body :X
    type Bound >: body.Upper <: Bounds

    type Copy[+A <: Bound] <: Template[A]

    type High[T[+A <: Narrow] <: Bounds]

//    def applied(narrow :Template[Narrow]) :High[narrow.Copy] //this fails
    def indirect(narrow :Template[Narrow]) :High[({ type T[+A <: Narrow] = narrow.Copy[A] })#T] //but this works
  }

  trait Narrow extends Bounds {
    type Upper >: Narrow <: Bounds
  }
}
