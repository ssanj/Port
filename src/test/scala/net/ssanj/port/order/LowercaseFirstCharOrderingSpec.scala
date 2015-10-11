package net.ssanj.port.order

import org.scalatest.{Matchers, WordSpecLike}
import LowercaseFirstCharOrdering._

final class LowercaseFirstCharOrderingSpec extends Matchers with WordSpecLike {
  "A LowercaseFirstCharOrder" should {
    "order lower characters first" when {
      "compared with uppercase characters" in {
        order.compare('a', 'A') should be < (0)
      }
    }

    "order uppercase characters last" when {
      "compared with lowercase characters" in {
        order.compare('A', 'a') should be > (0)
      }
    }

    "order lowercase characters as per alphabet" when {
      "compared with other lowercase characters" in {
        order.compare('b', 'c') should be < (0)
        order.compare('h', 'h') should be (0)
        order.compare('z', 'a') should be > (0)
      }
    }

    "order uppercase characters as per alphabet" when {
      "compared with other uppercase characters" in {
        order.compare('P', 'T') should be < (0)
        order.compare('Q', 'Q') should be (0)
        order.compare('Z', 'S') should be > (0)
      }
    }

    "order underscore character last" when {
      "compared with upper or lowercase characters" in {
        order.compare('Z', '_') should be < (0)
        order.compare('z', '_') should be < (0)
        order.compare('_', 'a') should be > (0)
        order.compare('_', 'A') should be > (0)
        order.compare('_', '_') should be (0)
      }
    }

    "order period character first" when {
      "compared with upper, lower or underscore characters" in {
        order.compare('.', 'a') should be < (0)
        order.compare('.', 'A') should be < (0)
        order.compare('.', '_') should be < (0)

        order.compare('z', '.') should be > (0)
        order.compare('Z', '.') should be > (0)
        order.compare('_', '.') should be > (0)

      }
    }

    "order curly braces last" when {
      "compared with upper and lowercase characters" in {
        order.compare('{', 'z') should be > (0)
        order.compare('{', 'Z') should be > (0)
        order.compare('}', 'z') should be > (0)
        order.compare('}', 'Z') should be > (0)

        order.compare('}', '}') should be  (0)
        order.compare('{', '}') should be < (0)
        order.compare('}', '{') should be > (0)
        order.compare('{', '{') should be (0)

        order.compare('_', '{') should be < (0)
        order.compare('_', '}') should be < (0)
        order.compare('{', '_') should be > (0)
        order.compare('}', '_') should be > (0)
      }
    }
  }
}