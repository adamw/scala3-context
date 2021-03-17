package context

import scala.concurrent.ExecutionContext

object Step4 extends App:
  trait Connection
  // aka ConnectionIO = Connected[IO[*]], DBIOAction
  type Connected[T] = Connection ?=> T
  
  case class User(email: String)
  
  class DB {
    def transact[T](f: Connected[T]): T = ???
  }
  
  class UserModel {
    def find(email: String): Connected[Option[User]] = ???
    def saveUser(u: User): Connected[Unit] = ???
  }
  
  class UserService(userModel: UserModel) {
    def newUser(u: User): Connected[Boolean] = {
      userModel.find(u.email) match {
        case Some(_) => false
        case None => 
          userModel.saveUser(u)
          true
      }
    }
  }

  class Api(userService: UserService, db: DB) {
    val result: Boolean = db.transact(userService.newUser(User("x@y.pl")))
    val result2: Int = db.transact(10)
  }