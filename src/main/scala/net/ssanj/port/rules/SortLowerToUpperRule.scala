package net.ssanj.port.rules

import net.ssanj.port.PortTypes._

trait SortLowerToUpperRule {
  def sortLowerToUpper: ImportRule =  _.sortAscending
}