package net.ssanj.port

import scala.util.matching.Regex

import PortTypes._


object Rules {

  val importGroupR = new Regex("""\{(.+)\}""", "values")
  //ideally what we want is
  // _ < _ if lower case
  //if a mix of upper and lower then lowercase comes first.
  def sortLowerToUpper: ImportRule = _.flatMap(values => Group(values.sortWith(_.value < _.value)))

  //ImportDesc => ImportDesc
  // def sortWithinGroupImport: ImportRule = {
  //   case s@Single(imp) =>
  //     val matchesOp = importGroupR findFirstMatchIn imp.value
  //     matchesOp.fold(s) { matches =>
  //       val newOrder = matches.
  //                       matched.
  //                       replaceAll("""[{}]""", "").
  //                       split(",").
  //                       map(_.trim).
  //                       sortWith(_ < _).
  //                       mkString(", ")

  //       Single(imp.map(value => importGroupR replaceAllIn(value, m => s"{$newOrder}")))
  //     }

  //   case Group(imps) =>
  //     //we should be able to do this with a Monoid/Semigroup
  //     val x:Seq[ImportDesc] = imps.map(Single(_)).map(sortWithinGroupImport)
  //     Group(x.foldLeft(Seq.empty[Import]){ (a, v) =>
  //       v match {
  //         case Single(value) =>  a :+ value
  //         case Group(values) => a ++ values
  //       }
  //     })
  // }
}