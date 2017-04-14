object Homework_010 {
  def main(args: Array[String]): Unit = {
    println(answer())
  }

  def answer(): Long = {
    2.toLong.to(2000000.toLong).filter(isPrime).toList.sum
  }

  def isPrime(n: Long): Boolean = n >= 2 && 2.to(math.sqrt(n).toInt).forall(n % _ != 0)

}
