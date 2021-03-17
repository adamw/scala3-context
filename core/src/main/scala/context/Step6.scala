package context

import scala.concurrent.ExecutionContext

object Step6 extends App:

  trait Connection
  type Connected[T] = Connection ?=> T

  trait Clock
  type Temporal[T] = Clock ?=> T

  case class IO[T](run: () => T) {
    def flatMap[U](other: T => IO[U]): IO[U] = IO(() => other(run()).run())
  }

  val p1: Temporal[IO[Int]] = ???
  val p2: Connected[IO[String]] = ???

  val r1: Temporal[Connected[IO[String]]] = p1.flatMap(_ => p2)
  val r2: Connected[Temporal[IO[String]]] = p1.flatMap(_ => p2)
  //val r3 = p1.flatMap(_ => p2)

  // ZIO[R, E, A] ~ R => Either[E, A]
  // + resource management