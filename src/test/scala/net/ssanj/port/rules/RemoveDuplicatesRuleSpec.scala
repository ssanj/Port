package net.ssanj.port.rules

import org.scalatest.{Matchers, WordSpecLike}
import net.ssanj.port.PortTypes._
import net.ssanj.port.PortTypes.Import._

final class RemoveDuplicateRuleSpec extends Matchers with WordSpecLike {
  "A RemoveDuplicate Rules" should {
    "remove duplicate imports" in {
    val rule = new RemoveDuplicatesRule{}

      val imports = createImports(
        "import play.api.Logger",
        "import java.net.URLDecoder",
        "import play.api.Logger"
      )

      val result = rule.removeDuplicates(ImportGroup(imports))
      result.values.map(_.value) should contain allOf (
        "import play.api.Logger",
        "import java.net.URLDecoder"
      )
    }
  }
}