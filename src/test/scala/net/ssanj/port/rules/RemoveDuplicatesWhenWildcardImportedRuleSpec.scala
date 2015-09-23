package net.ssanj.port.rules

import org.scalatest.{Matchers, WordSpecLike}

import net.ssanj.port.PortTypes._
import net.ssanj.port.PortTypes.Import._

final class RemoveDuplicatesWhenWildcardImportedSpec extends Matchers with WordSpecLike {
  "A RemoveDuplicatesWhenWildcardImported Rule" should {
    "remove duplicate direct imports" when {
      "a wildcard import has already imported it" in {
        val rule = new RemoveDuplicatesWhenWildcardImportedRule{}

        val imports = createImports(
          "import net.ssanj.port.PortTypes.Import._",
          "import scala.util.Try",
          "import net.ssanj.port.PortTypes.Import.ImportGroup",
          "import scala.util._",
          "import net.ssanj.port.PortTypes.Import.createImports",
          "import scala.concurrent.Duration",
          "import scala.util.Success",
          "import scala.util.Failure",
          "import scala.concurrent.Future"
        )

        val result = rule.removeDuplicatesWhenWildcardImported(ImportGroup(imports))
        result.values.map(_.value) should contain theSameElementsAs(
          Seq("import net.ssanj.port.PortTypes.Import._",
              "import scala.util._",
              "import scala.concurrent.Future",
              "import scala.concurrent.Duration"
          ))
      }
    }
  }
}