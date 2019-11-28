object GenericExistentials {
  class Foo[IN, OUT]
  class Bar[OUT](v: Foo[_, OUT])
  class FooExt[T <: Foo[_, _]](foo: T) {
//    new Bar(foo)
  }
}
