object OverloadedSingleton {
  class Class {
    singleton[this.type](this)
  }

  def singleton[T <: Class](t :T) :T = t

//  def singleton[T](t :Class, i :Int) = t
}
