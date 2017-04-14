object Homework_04 {
  def main(args: Array[String]): Unit = {
    println(checkMaxPalindromicNumber())
  }

  def checkMaxPalindromicNumber(): Int = {
    var palindromicList: List[Int] = List()
    for (
      i <- 999 to 100 by -1;
      j <- i to 100 by -1
    ) {
      if ((i * j).toString.equals((i * j).toString.reverse)) {
        // return i * j
        palindromicList = (i * j) :: palindromicList
      }
    }
    palindromicList.max
  }
}
