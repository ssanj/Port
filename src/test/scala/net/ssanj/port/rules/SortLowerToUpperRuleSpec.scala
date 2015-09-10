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
        "controllers.ExecutionHelpers",
        "java.net.URLDecoder",
        "java.util.concurrent.Executors",
        "nl.grons.metrics.scala.MetricName",
        "org.joda.time.{DateTimeZone, LocalDateTime}",
        "play.api.Logger",
        "scala.concurrent.duration._",
        "scala.concurrent.{ExecutionContext, Future}",
        "scala.language.postfixOps",
        "scalacache.memoization._",
        "util.{Error, Result}"
      )
    }
  }
}