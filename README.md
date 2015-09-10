# Port #

This is a proof-of-concept for a Sublime plugin I plan to create to sort scala imports.

## Some thoughts on rules ##

* In a grouped import, one with {}, an _ appears last.
* Should imports that are aliased with => should be sorted based on their original name or their new name?
* Some imports have __root_. These should ideally be before other imports.
* Supports fully qualified imports not partial imports

  Example:

  don't use:

  import somelib.SomeObject._
  import NestedObject.Blah

  use:

  import someLib.SomeObject._
  import someLib.SomeObject.NestedObject.Blah

* packages are sorted alphabetically from lowercase to uppercase.

* Remove duplicate imports
  - same import twice
* Remove unnecessary import
  - an import from an ._ is explicitly imported again.
       Example:
       import somelib.SomeObject._
       import somelib.SomeObject.Blah (this is unnecessary)
  - an import that is imported individually and in a group
       Example:
       import somelib.{ABC, XYZ} <- prefer grouped imports.
       import somelib.XYZ <- remove this.

notes:

There are three ways to form an identifier.

First, an identifier can start with a letter which can be followed by an arbitrary sequence of letters and digits. This may be followed by underscore ‘_‘ characters and another string composed of either letters and digits or of operator characters.

Second, an identifier can start with an operator character followed by an arbitrary sequence of operator characters. The preceding two forms are called plain identifiers.

Finally, an identifier may also be formed by an arbitrary string between back-quotes (host systems may impose some restrictions on which strings are legal for identifiers). The identifier then is composed of all characters excluding the backquotes themselves.
