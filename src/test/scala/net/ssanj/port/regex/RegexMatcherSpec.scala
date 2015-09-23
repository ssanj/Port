package net.ssanj.port.regex

import org.scalatest.{Matchers, OptionValues, WordSpecLike}

final class RegexMatcherSpec extends Matchers with WordSpecLike with OptionValues {

  "A Regex Matcher with wildcard import" should {

    val reg = RegexMatcher.wildcardImport

    "match" when {
      "given a wildcard import" in {
        val result = reg.unapplySeq("import scala.util.matching._")
       result.value should contain only ("scala.util.matching")
      }

      "given a wildcard import with extra spaces" in {
        val result = reg.unapplySeq("import      scala.util.matching._")
       result.value should contain only ("scala.util.matching")
      }
    }

    "not match" when {
      "it does not contain a wildcard" in {
        val result = reg.unapplySeq("import scala.collection.immutable.Seq")
        result should be (None)
      }
    }
  }

  "A Regex Matcher with directPackageImports" should {
    val reg = RegexMatcher.directPackageImports("scala.collection.immutable")

    "match" when {
      "given a prefix for a package that has a direct import" in {
        val result = reg.unapplySeq("import scala.collection.immutable.Seq")
        withClue("Expected match for direct package: scala.collection.immutable.Seq") {
          result.isDefined should be (true)
        }
      }
    }

    "not match" when {
      "given a prefix for a package that is not a direct import" in  {
        val result = reg.unapplySeq("import scala.collection.immutable.XYZ.ABC")
        withClue("Expected mismatch for indirect package: scala.collection.immutable.XYZ.ABC") {
          result.isDefined should be (false)
        }
      }
    }
  }
}

