import scala.annotation.unchecked.uncheckedVariance

object UncheckedVariance {
  trait BIO[F[+_, +_]]
  trait EitherT[F[_], E, A]
//  def makeWithNativeTypeProjection[F[_]] =
//    new BIO[({type L[+E, +A] = EitherT[F, E @uncheckedVariance, A @uncheckedVariance]})#L] {}
}
