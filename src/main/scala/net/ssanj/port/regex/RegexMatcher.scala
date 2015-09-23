package net.ssanj.port.regex

import scala.util.matching.Regex

object RegexMatcher {

  /**
   * Extracts the package preceding a wildcard import
   * Given:
   * {{{ "import scala.util.matching._" }}}
   *
   * Matches:
   * {{{ "scala.util.matching" }}}
   *
   * Also removes any preceding spaces between the import statement and the package.
   * Given:
   *
   * {{{ "import     scala.util.matching._" }}}
   *
   * Matches:
   * {{{ "scala.util.matching" }}}
   *
   */
  val wildcardImport = """import\s*(.+)\._$""".r

  /**
   * Creates a regular expression given a package prefix such that only direct class of that package are
   * matched.
   * Given: a prefix of scala.collection.immutable
   *
   * Matches:
   * {{{ import scala.collection.immutable.Seq }}}
   * {{{ import scala.collection.immutable.Map }}}
   *
   * Does not match:
   * {{{ import scala.collection.immutable.Seq.fill }}}
   * {{{ import scala.collection.immutable.XYZ.ABC }}}
   */
  // def directPackageImports(prefix: String): Regex = s"""import ${prefix}.(?!.*\\.)[A-Z$$][a-zA-Z$$]*$$""".r
  def directPackageImports(prefix: String): Regex = s"""import ${prefix}.(?!.*\\.)[a-zA-Z$$]+$$""".r
}