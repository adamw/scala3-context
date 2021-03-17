package context

object Step2 extends App:
  val g: Int ?=> Boolean ?=> String = s"Got: ${summon[Int]} ${summon[Boolean]}"
  
  def run1(f: Int ?=> Boolean ?=> String): Unit = println(f(using 9)(using false))
  def run2(f: Boolean ?=> Int ?=> String): Unit = println(f(using true)(using 10))
  def run3(f: (Boolean, Int) ?=> String): Unit = println(f(using false, 11))

  run1(g)
  run2(g)
  run3(g)