package net.ssanj.port.rules

import org.scalatest.{Matchers, WordSpecLike}
import net.ssanj.port.PortTypes._
import net.ssanj.port.PortTypes.Import._

final class SortWithinGroupImportRuleSpec extends Matchers with WordSpecLike {
  "A SortWithinGroupImport Rule" should {
    "sort the members ascending" when {
      "they are part of a group" in {
        val rule = new SortWithinGroupImportRule {}
        val imports = createImports("import com.otherlevels.models.contentTracking.{RetrievalCountRow, Zero, RetrievalCount, Ascending}")
        val result = rule.sortWithinGroupImport(ImportGroup(imports))

        result.values.map(_.value) should contain only (
          "import com.otherlevels.models.contentTracking.{Ascending, RetrievalCount, RetrievalCountRow, Zero}"
        )
      }
    }

    "sort the _ last" when {
        "it is part of a group" in {
          val rule = new SortWithinGroupImportRule {}
          val imports = createImports("import com.otherlevels.models.contentTracking.{RetrievalCountRow, Zero, _, RetrievalCount, Ascending}")
          val result = rule.sortWithinGroupImport(ImportGroup(imports))

          result.values.map(_.value) should contain only (
            "import com.otherlevels.models.contentTracking.{Ascending, RetrievalCount, RetrievalCountRow, Zero, _}"
          )
        }
    }
  }
}