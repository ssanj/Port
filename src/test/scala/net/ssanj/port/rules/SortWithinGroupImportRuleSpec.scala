package net.ssanj.port.rules

import org.scalatest.{Matchers, WordSpecLike}
import net.ssanj.port.PortTypes._
import net.ssanj.port.PortTypes.Import._

final class SortWithinGroupImportRuleSpec extends Matchers with WordSpecLike {

  val rule = new SortWithinGroupImportRule {}

  "A SortWithinGroupImport Rule" should {
    "sort the members ascending" when {
      "they are part of a group" in {
        val imports = createImports("import com.otherlevels.models.contentTracking.{RetrievalCountRow, Zero, RetrievalCount, Ascending}")
        val result = rule.sortWithinGroupImport(ImportGroup(imports))

        result.values.map(_.value) should contain only (
          "import com.otherlevels.models.contentTracking.{Ascending, RetrievalCount, RetrievalCountRow, Zero}"
        )
      }
    }

    "sort the _ last" when {
      "it is part of a group" in {
        val imports = createImports("import com.otherlevels.models.contentTracking.{RetrievalCountRow, Zero, _, RetrievalCount, Ascending}")
        val result = rule.sortWithinGroupImport(ImportGroup(imports))

        result.values.map(_.value) should contain only (
          "import com.otherlevels.models.contentTracking.{Ascending, RetrievalCount, RetrievalCountRow, Zero, _}"
        )
      }
    }

    "sort by original name" when {
      "given an alias as part of a group" in {
        val imports = createImports("import com.otherlevels.models.contentTracking.{RetrievalCountRow => Count, RetrievalCount}")
        val result = rule.sortWithinGroupImport(ImportGroup(imports))

        result.values.map(_.value) should contain only (
          "import com.otherlevels.models.contentTracking.{RetrievalCount, RetrievalCountRow => Count}"
        )
      }
    }

    "sort multiple imports" when {
      "given a mixture of grouped and ungrouped imports" in {
        val imports = createImports("import net.ssanj.port.PortTypes._",
                                    "import org.scalatest.{WordSpecLike, Matchers}",
                                    "import scala.collection.AbstractIterator",
                                    "import scala.util.matching.{Regex}")
        val result = rule.sortWithinGroupImport(ImportGroup(imports))
        result.values.map(_.value) should contain inOrder (
          "import net.ssanj.port.PortTypes._",
          "import org.scalatest.{Matchers, WordSpecLike}",
          "import scala.collection.AbstractIterator",
          "import scala.util.matching.{Regex}"
        )
      }
    }
  }
}