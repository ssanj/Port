package net.ssanj.port

//fully qualified imports only
//so don't do:
// import PortTypes._
// import Import._
import PortTypes.Import._
import PortTypes._
import Rules._

object Demo extends App {
 // val importOp = createImport("import play.api.Logger")
 val importsOp = createImports(
  Seq(
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
  ))

  val resultOp = importsOp.map(ImportGroup.apply).map(sortLowerToUpper)
  resultOp.foreach { x => println(Printer.print(x)) }

  // val groupedImportsOp =
  //   createImport(
  //   "import com.otherlevels.models.contentTracking.{RetrievalCountRow, RetrievalCount}").
  //   map(Single).
  //   map(sortWithinGroupImport)

  // groupedImportsOp.foreach { x => println(Printer.print(x)) }
}
