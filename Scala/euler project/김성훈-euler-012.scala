object Homework_012 {
  def main(args: Array[String]): Unit = {
    println(triangularNumber.find(getDivisorSize(_) >= 500).head)
  }
  val triangularNumber: Stream[Int] = Stream.from(1).scan(0)(_ + _)

  def getDivisorSize(n: Int): Int = (1 to math.sqrt(n).toInt).count(n % _ == 0) * 2

  // 베낀거
  /*def getDivisorSize(n: Int): Int = {
    val middle = math.sqrt(n).toInt
    val halfDivisorSize = (1 to middle).count(n % _ == 0)
    halfDivisorSize * 2 - (if (middle * middle == 2) 1 else 0)
  }*/

  // 너무 오래걸림..
  def getTriangularNumber(n: Int): Int = 1.to(n).sum
  def getDivisorSize2(n: Int): Int = 1.to(n).filter(n % _ == 0).toList.size
}
