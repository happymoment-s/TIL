/**
  * Created by SungHoon on 2017-03-04.
  */
object Homework_007 {
  def main(args: Array[String]): Unit = {
        println(findNthPrime(10001, 2, 0))
  }

  def isPrime(n: Int): Boolean = n >= 2 && 2.to(math.sqrt(n).toInt).forall(n % _ != 0)

  def findNthPrime(n: Int, number: Int, primeIndex: Int): Long = {
    if (n == primeIndex) number - 1
    else if (isPrime(number)) findNthPrime(n, number + 1, primeIndex + 1)
    else findNthPrime(n, number + 1, primeIndex)
  }
}
