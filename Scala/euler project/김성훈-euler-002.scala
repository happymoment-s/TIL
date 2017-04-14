object Homework_02 {
  def main(args: Array[String]): Unit = {
    def fibonacci(x:Int, y:Int, sum:Int): AnyVal = {
      if ((x + y) % 2 == 0) fibonacci(y, x + y, sum + x + y)
      else if ((x + y) > 4000000) sum
      else fibonacci(y, x + y, sum)
    }
    println("sum : " + fibonacci(0, 1, 0))
  }
}
