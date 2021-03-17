package context

object Step1 extends App:
  val f: Int => String = (x: Int) => s"Got: $x"
  val g: Int ?=> String = s"Got: ${summon[Int]}"

  val ff: Function1[Int, String] = f
  val gg: ContextFunction1[Int, String] = g

  println(f(10))

  println(g(using 16))
  
  println {
    given Int = 42
    g
  }
