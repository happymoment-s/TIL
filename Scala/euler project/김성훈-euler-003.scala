object Homework_03 {
  def main(args: Array[String]): Unit = {
    val number = 600851475143L
    println("factorization1 : " + factorization1(number))
    println("factorization2 : " + factorization2(number).last)
  }

  def factorization1(n: Long): Long = {
    var maxPrime = 0
    var number = n
    for (i <- 2 to math.sqrt(number).toInt) {
      while (number % i == 0) {
        number /= i
        if (maxPrime < i)
          maxPrime = i
//        println(i)
      }
    }
    maxPrime
  }

  def factorization2(n: Long): List[Long] = (2 to math.sqrt(n).toInt).find(n % _ == 0).fold(List(n))(i => i.toLong :: factorization2(n / i))
}
