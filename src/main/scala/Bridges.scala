object Bridges {
  type Id[A] = A
  def a: PartialFunction[Id[String], Boolean] = { case _ => true }
  def b: PartialFunction[String, Boolean] = { case _ => true }
}
