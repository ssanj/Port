package net.ssanj.port.rules

import scala.util.matching.Regex

import net.ssanj.port.PortTypes._
import net.ssanj.port.regex.RegexMatcher.{directPackageImports, wildcardImport}

trait RemoveDuplicatesWhenWildcardImportedRule {
  def removeDuplicatesWhenWildcardImported: ImportRule = group => {
    //filter out all wildcards
    //filter out all non-wildcards
    //for each wildcard find matches in the non-wildcard list
    //remove each match

    //Option[List[String]] => Boolean
    val partionByWildcards = group.values.partition(i => isMatch(wildcardImport)(i.value))
    val wildcards: Seq[Import] = partionByWildcards._1
    val directImports: Seq[Import] = partionByWildcards._2

    ImportGroup(wildcards.foldLeft(directImports){ (a, v) =>
      v.value match {
        case wildcardImport(prefix) =>
          val r = directPackageImports(prefix)
          a.filterNot(i => isMatch(r)(i.value))
        case _ => a
      }
    } ++ wildcards)
  }

  private def isMatch(r: Regex)(value: String): Boolean = {
    r.unapplySeq(value).fold(false)(_ => true)
  }
}