package net.ssanj.port.rules

import net.ssanj.port.PortTypes._

trait RemoveDuplicatesRule {
  def removeDuplicates: ImportRule = group => ImportGroup(group.values.toSet.toSeq)
}