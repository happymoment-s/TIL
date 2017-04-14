object Homework_005 {
  def main(args: Array[String]): Unit = {
    println(1.toLong.until(20.toLong).reduceLeft((x, y) => lcm(x, y)))
  }

  def lcm(a: Long, b: Long): Long = (a * b) / gcd(a, b)

  def gcd(a: Long, b: Long): Long = if (b == 0) a else gcd(b, a % b)
}
