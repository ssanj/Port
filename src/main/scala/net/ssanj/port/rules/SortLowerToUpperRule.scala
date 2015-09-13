package net.ssanj.port.rules

import net.ssanj.port.PortTypes._

trait SortLowerToUpperRule {
  def sortLowerToUpper: ImportRule = _.map(_.sortWith(_.value < _.value))
}