package net.ssanj.port.order

object LowercaseFirstCharOrdering {

  /**
   * We need to sort lowercase characters before uppercase characters.
   * This is the opposite of the default. In addition we want to have an _
   * sort after any other values. Comparing an _ and any character should be > 0.
   * {} always come last as per ascii table.
   * . always come first as per ascii table.
   */

  private val BEFORE = -1
  private val AFTER = 1
  private val EQ = 0

  val order = new Ordering[Char] {
    override def compare(c1: Char, c2: Char): Int = {
      if (c1 == '_' && (c2.isUpper || c2.isLower)) AFTER
      else if ((c1.isUpper || c1.isLower) && c2 == '_') BEFORE
      else if (c1.isUpper && c2.isLower) AFTER
      else if (c1.isLower && c2.isUpper) BEFORE
      else c1.compare(c2)
    }
  }
}