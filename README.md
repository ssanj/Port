# Port #

This is a proof-of-concept for a Sublime plugin I plan to create to sort Scala imports.

## Some thoughts on rules ##

* In a grouped import, (one with {}), an _ appears last.
* Should imports that are aliased with => should be sorted based on their original name or their new name?
* Some imports have __root_. These should ideally be before other imports.

### Use fully qualified imports ###

  don't use:

  ```scala
  import somelib.SomeObject._
  import NestedObject.Blah
  ```
  use:

  ```scala
  import someLib.SomeObject._
  import someLib.SomeObject.NestedObject.Blah
  ```

### Sorted alphabetically ###

  given:

  ```scala
  "import play.api.Logger",
  "import java.net.URLDecoder",
  "import java.util.concurrent.Executors",
  "import controllers.ExecutionHelpers"
  ```

  sort to this:

  ```scala
    "import controllers.ExecutionHelpers",
    "import java.net.URLDecoder",
    "import java.util.concurrent.Executors",
    "import play.api.Logger"

  ```
### Remove duplicates ###

  ```scala
  import somelib.SomeObject
  import somelib.SomeObject <- remove this
  ```
### Remove unnecessary imports ###
  - an import from an ._ is explicitly imported again.

       ```scala
       import somelib.SomeObject._
       import somelib.SomeObject.Blah (this is unnecessary)
       ```
  - an import that is imported individually and in a group

       ```scala
       import somelib.{ABC, XYZ} <- prefer grouped imports.
       import somelib.XYZ <- remove this.
       ```

## Notes ##

There are three ways to form an identifier.

First, an identifier can start with a letter which can be followed by an arbitrary sequence of letters and digits. This may be followed by underscore ‘_‘ characters and another string composed of either letters and digits or of operator characters.

Second, an identifier can start with an operator character followed by an arbitrary sequence of operator characters. The preceding two forms are called plain identifiers.

Finally, an identifier may also be formed by an arbitrary string between back-quotes (host systems may impose some restrictions on which strings are legal for identifiers). The identifier then is composed of all characters excluding the backquotes themselves.
