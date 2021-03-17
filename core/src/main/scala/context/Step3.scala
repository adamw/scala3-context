package context

import scala.concurrent.ExecutionContext

object Step3 extends App:
  def before = {
    case class User(email: String)
    def newUser(u: User)(implicit ec: ExecutionContext): Boolean = {
      lookupUser(u.email) match {
        case Some(_) => false
        case None =>
          saveUser(u)
          true
      }
    }
    def lookupUser(email: String)(implicit ec: ExecutionContext): Option[User] = ???
    def saveUser(u: User)(implicit ec: ExecutionContext): Unit = ???
  }
  
  def after = {
    type Executable[T] = ExecutionContext ?=> T
    
    case class User(email: String)
    def newUser(u: User): Executable[Boolean] = {
      lookupUser(u.email) match {
        case Some(_) => false
        case None =>
          saveUser(u)
          true
      }
    }
    def lookupUser(email: String): Executable[Option[User]] = ???
    def saveUser(u: User): Executable[Unit] = ???
  }