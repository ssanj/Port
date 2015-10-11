package net.ssanj.port

object PortTypes {

  final case class Import private (value: String)

  object Import {
    //TODO: Add some regex here to ensure we have valid imports.
    //TODO: Should we check for padding between the "import" statement and the package path?
    def createImports(values: String*): Seq[Import] =
      values.filter(_.startsWith("import ")).map(x => Import(x.trim))

    val importAscendingOrder = new Ordering[Import] {
      override def compare(i1: Import, i2: Import): Int = {
        //if one is Upper and one is lower, then lower comes before Upper
        //_ comes last
        //if all the same case then "normal" order of Strings
        //
        i1.value.compare(i2.value)
      }
    }
  }

  final case class ImportGroup(values: Seq[Import]) {
    def mapAll(f: Seq[Import] => Seq[Import]): ImportGroup = ImportGroup(f(values))

    def sortAscending(): ImportGroup = ImportGroup(values.sorted(Import.importAscendingOrder))
  }

  type ImportRule = ImportGroup => ImportGroup
}