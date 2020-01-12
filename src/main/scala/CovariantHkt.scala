import scala.language.higherKinds

object CovariantHkt {
//  trait MonoIO[F[_]]
//  trait BifunctorIO[F[+_, _]]
//  case class IO[+A]()
//  object IO {
//    implicit val monoInstance: MonoIO[IO] = new MonoIO[IO]{}
//  }
//
//  trait AnyIO[+F[_]]
//  object AnyIO {
//    implicit def fromMono[F[_]: MonoIO]: AnyIO[F] = new AnyIO[F] { }
//    implicit def fromBIO[F[+_, _]: BifunctorIO]: AnyIO[({ type K[A] = F[Nothing, A] })#K] =
//      new AnyIO[({ type K[A] = F[Nothing, A] })#K] { }
//  }

//  implicitly[AnyIO[IO]]

  class MonoIO[F] // it works if you make this covariant

  class IO
  object IO { implicit val monoInstance: MonoIO[IO] = new MonoIO[IO] }
//--
  class AnyIO[+F] // also works if you drop covariance here (and don't add it above)
  object AnyIO { implicit def fromMono[F](implicit mono: MonoIO[F]): AnyIO[F] = new AnyIO[F] }

  object Test {
    // if we provide this explicitly, it works even for any mix of variance:
    // implicit val ev = AnyIO.fromMono[IO]

//    implicitly[AnyIO[IO]] //(AnyIO.fromMono) even with this hint, type inference still fails
  }
}
