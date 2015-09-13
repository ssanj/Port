package net.ssanj.port.rules

import net.ssanj.port.PortTypes._
import net.ssanj.port.PortTypes.Import._

import org.scalatest.{Matchers, WordSpecLike}

final class SortLowerToUpperRuleSpec extends Matchers with WordSpecLike {

  "A SortLowerToUpper Rule" should {
    "sort imports from lower to uppercase" in {
      val rule = new SortLowerToUpperRule{}

      val imports = createImports(
        "import play.api.Logger",
        "import java.net.URLDecoder",
        "import java.util.concurrent.Executors",
        "import controllers.ExecutionHelpers",
        "import util.{Error, Result}",
        "import scala.concurrent.{ExecutionContext, Future}",
        "import nl.grons.metrics.scala.MetricName",
        "import scalacache.memoization._",
        "import org.joda.time.{DateTimeZone, LocalDateTime}",
        "import scala.concurrent.duration._",
        "import scala.language.postfixOps"
      )

      val result = rule.sortLowerToUpper(ImportGroup(imports))
      result.value.map(_.value) should contain inOrder (
        "import controllers.ExecutionHelpers",
        "import java.net.URLDecoder",
        "import java.util.concurrent.Executors",
        "import nl.grons.metrics.scala.MetricName",
        "import org.joda.time.{DateTimeZone, LocalDateTime}",
        "import play.api.Logger",
        "import scala.concurrent.duration._",
        "import scala.concurrent.{ExecutionContext, Future}",
        "import scala.language.postfixOps",
        "import scalacache.memoization._",
        "import util.{Error, Result}"
      )
    }
  }
}