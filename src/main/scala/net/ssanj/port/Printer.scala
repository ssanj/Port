package net.ssanj.port

import PortTypes._

object Printer {

  def print(id: ImportGroup): String = id.value.map(_.value).mkString("\n")
}