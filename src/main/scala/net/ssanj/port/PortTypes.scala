package net.ssanj.port

object PortTypes {

  final case class Import private (value: String)

  object Import {
    //TODO: Add some regex here to ensure we have valid imports.
    //TODO: Should we check for padding between the "import" statement and the package path?
    def createImports(values: String*): Seq[Import] =
      values.filter(_.startsWith("import ")).map(x => Import(x.trim))
  }

  final case class ImportGroup(values: Seq[Import]) {
    def map(f: Seq[Import] => Seq[Import]): ImportGroup = ImportGroup(f(values))
  }

  type ImportRule = ImportGroup => ImportGroup
}