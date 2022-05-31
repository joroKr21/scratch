import scala.quoted.*

object ShowTypeLambda {
  def showMacro[A <: AnyKind: Type](using Quotes) =
    Expr(Type.show[A])

  inline def show[A <: AnyKind]: String =
    ${ showMacro[A] }
}
