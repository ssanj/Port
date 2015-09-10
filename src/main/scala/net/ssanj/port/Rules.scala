package net.ssanj.port

import scala.util.matching.Regex

import net.ssanj.port.PortTypes._
import net.ssanj.port.rules.SortLowerToUpperRule


object Rules extends SortLowerToUpperRule {

  val importGroupR = new Regex("""\{(.+)\}""", "values")
  //ideally what we want is
  // _ < _ if lower case
  //if a mix of upper and lower then lowercase comes first.
  // def sortLowerToUpper: ImportRule = _.flatMap(values => Group(values.sortWith(_.value < _.value)))

  //ImportDesc => ImportDesc
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

        imp.map(value => importGroupR replaceAllIn(value, m => s"{$newOrder}"))
      }
    }

    group.map(_.map(sortGroupMembers))
  }
}