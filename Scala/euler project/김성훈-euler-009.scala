/**
  * Created by SungHoon on 2017-03-11.
  */
object Homework_009 {
  def main(args: Array[String]): Unit = {
    getAnswer()
    getAnswer2()
  }

  def getAnswer(): Unit = {
    for (a <- 1 until 1000) {
      for (b <- a + 1 until 1000) {
        val c = getC(a, b)
        if (isPythagoreanTriple(a, b, c))
          println(a + " * " + b + " * " + c + " = " + a * b * c)
      }
    }
  }

  def getAnswer2(): Unit = {
    for (a <- 1 until 1000;
         b <- a + 1 until 1000;
         c = getC(a, b)
         if isPythagoreanTriple(a, b, c)
    ) yield println(a + " * " + b + " * " + c + " = " + a * b * c)
  }

  def getC(a: Int, b: Int): Int = 1000 - b - a

  def isPythagoreanTriple(a: Int, b: Int, c: Int): Boolean = {
    if ((a < b && b < c) && (a * a + b * b == c * c)) true
    else false
  }
}
