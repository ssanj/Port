package net.ssanj.port

import PortTypes._

object Printer {

  def print(id: ImportGroup): String = id.values.map(_.value).mkString("\n")
}