object Homework_006 {
  def main(args: Array[String]): Unit = {
    println(square(1.to(100).sum) - 1.to(100).map(square).sum)
  }

  def square(x: Int): Int = x * x
}
