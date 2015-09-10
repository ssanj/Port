package net.ssanj.port

object PortTypes {

  final case class Import private (value: String) {
    //TODO: Does this make any sense? We are verifying that
    //the import is valid and then allowing any String
    //to inhabit this class. Seems wrong.
    def map(f: String => String): Import = Import(f(value))
  }

  object Import {
    //TODO: Add some regex here to ensure we have valid imports.
    //TODO: We don't need an Option here as Seq.empty will cover the empty case.
    def createImports(values: String*): Seq[Import] =
      values.filter(_.startsWith("import ")).
                    map(_.substring("import ".length)).
                    map(Import(_))
  }

  final case class Group[A](value: A) {
    def map[B](f: A => B): Group[B] = Group(f(value))
    def flatMap[B](f: A => Group[B]): Group[B] = f(value)
  }

  type Rule[A] = Group[A] => Group[A]

  type ImportGroup = Group[Seq[Import]]
  type ImportRule = ImportGroup => ImportGroup

  object ImportGroup {
    def apply(imports: Seq[Import]): ImportGroup = Group(imports)
  }
}