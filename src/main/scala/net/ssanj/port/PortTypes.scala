package net.ssanj.port

object PortTypes {

  final case class Import private (value: String)

  object Import {
    //TODO: Add some regex here to ensure we have valid imports.
    def createImports(values: String*): Seq[Import] =
      values.filter(_.startsWith("import ")).map(Import(_))
  }

  final case class ImportGroup(values: Seq[Import]) {
    def map[A](f: Seq[Import] => Seq[Import]): ImportGroup = ImportGroup(f(values))
  }

  type ImportRule = ImportGroup => ImportGroup
}