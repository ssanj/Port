package net.ssanj.port.rules

import net.ssanj.port.PortTypes._

trait SortLowerToUpperRule {
  def sortLowerToUpper: ImportRule = ig => ImportGroup(ig.value.sortWith(_.value < _.value))
}