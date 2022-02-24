import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

trait LZ77 {
  def encode(text: String): Array[LZ77.Run]
  def decode(runs: Array[LZ77.Run]): String
}


object LZ77 extends App {
  case class Run(offset: Int, length: Int, char: Char)

  trait Decoder extends LZ77 {
    override def decode(runs: Array[Run]): String = {
      val chars = new Array[Char](runs.map(_.length + 1).sum)
      val n = runs.length

      @tailrec def loop(i: Int, j: Int): String =
        if (i >= n) new String(chars) else {
          val Run(offset, length, char) = runs(i)
          for (k <- 0 until length) chars(j + k) = chars(offset + k)
          chars(j + length) = char
          loop(i + 1, j + length + 1)
        }

      loop(0, 0)
    }
  }

  object Naive extends Decoder {
    override def encode(text: String): Array[Run] = {
      // O(length)
      def containsSubstring(i: Int, j: Int, length: Int): Boolean =
        (0 until length).forall(k => text.charAt(i + k) == text.charAt(j + k))
      // O(length ^ 2)
      def indexOfSubstring(j: Int, length: Int): Int =
        (0 to j - length).indexWhere(containsSubstring(_, j, length))
      // O(log length * length ^ 2)
      @tailrec def binarySearch(i: Int, j: Int, len: Int, min: Int, max: Int): (Int, Int) =
        if (min >= max) (j, len) else {
          val mid = (min + max) / 2
          val k = indexOfSubstring(i, mid)
          if (k < 0) binarySearch(i, j, len, min, mid)
          else binarySearch(i, k, mid, mid + 1, max)
        }

      val n = text.length
      val runs = ArrayBuffer.empty[Run]
      // O(log length * length ^ 3)
      @tailrec def loop(i: Int): Array[Run] =
        if (i >= n) runs.toArray else {
          val (j, l) = binarySearch(i, -1, 0, 1, n - i)
          val run = if (j < 0) Run(0, 0, text.charAt(i))
            else if (i + l >= n) Run(j, l - 1, text.charAt(n - 1))
            else Run(j, l, text.charAt(i + l))
          runs += run
          loop(i + l + 1)
        }

      loop(0)
    }

    // O(length * log limit * limit ^ 2)
  }
}
