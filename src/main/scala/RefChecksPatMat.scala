class RefChecksPatMat {
  trait Bar
  trait Bar2[B <: Bar]
  class Foo[B <: Bar, B2[C <: Bar] <: Bar2[C]] {
    trait SomeThing
  }

  val x = new Bar {}

  x match {
//    case _: Foo[_, _]#SomeThing => println("A") //error
    case _ => println("B")
  }
}
