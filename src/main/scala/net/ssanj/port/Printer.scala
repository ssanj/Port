package net.ssanj.port

import PortTypes._

object Printer {

  def print(id: ImportDesc): String = {
    id match {
      case Single(value) => value.value
      case Group(values) => values.map(_.value).mkString("\n")
    }
  }
}