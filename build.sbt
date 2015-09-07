name := "port"

version := "0.0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
"org.scalaz" %% "scalaz-core" % "7.1.2",
"org.scalatest" %%  "scalatest" % "2.2.4" % "test"
)

scalacOptions ++= Seq(
                      "-unchecked",
                      "-deprecation",
                      "-feature",
                      "-Xfatal-warnings",
                      "-Xlint:_",
                      "-Ywarn-dead-code",
                      "-Ywarn-inaccessible",
                      "-Ywarn-unused-import",
                      "-Ywarn-infer-any",
                      "-Ywarn-nullary-override",
                      "-Ywarn-nullary-unit"
                  )

