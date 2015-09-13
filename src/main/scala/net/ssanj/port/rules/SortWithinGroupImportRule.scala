package net.ssanj.port.rules

import scala.util.matching.Regex
import net.ssanj.port.PortTypes._
import net.ssanj.port.PortTypes.Import.createImports


trait SortWithinGroupImportRule {

  val importGroupR = new Regex("""\{(.+)\}""", "values")

  def sortWithinGroupImport: ImportRule = { group =>

    def sortGroupMembers(imp: Import): Import = {
      val matchesOp = importGroupR findFirstMatchIn imp.value
      matchesOp.fold(imp) { matches =>
        val newOrder = matches.
                        matched.
                        replaceAll("""[{}]""", "").
                        split(",").
                        map(_.trim).
                        sortWith(_ < _).
                        mkString(", ")

        createImports(importGroupR replaceAllIn(imp.value, m => s"{$newOrder}")).
          fold(imp)((a, i) => i)
      }
    }

    ImportGroup(group.value.map(sortGroupMembers))
  }

}