package net.ssanj.port

object PortTypes {

  final case class Import private (value: String) {
    def map(f: String => String): Import = Import(f(value))
  }
 
  object Import {
    def createImport(value: String): Option[Import] = 
      if (value.startsWith("import ")) 
        Option(Import(value.substring("import ".length))) 
      else None

    //TODO: map and sequence with createImport  
    def createImports(values: Seq[String]): Option[Seq[Import]] =
      Option(values.filter(_.startsWith("import ")).
                    map(_.substring("import ".length)).
                    map(Import(_)))
  }

  sealed trait ImportDesc
  final case class Single(value: Import) extends ImportDesc
  final case class Group(values: Seq[Import]) extends ImportDesc

  type Rule = ImportDesc => ImportDesc
}