package context

import scala.concurrent.ExecutionContext

object Step5 extends App:
  trait Connection
  type Connected[T] = Connection ?=> T

  trait Clock
  type Temporal[T] = Clock ?=> T

  trait Resource
  
  def touch(r: Resource): Connected[Temporal[Unit]] = ???

  def update(r: Resource): Connected[Unit] = {
    given Clock = ???
    touch(r)
  }