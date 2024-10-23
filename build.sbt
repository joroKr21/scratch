name := "scratch"
version := "0.1"
scalaVersion := "2.13.15"
compileOrder := CompileOrder.JavaThenScala
scalacOptions += "-deprecation"
scalacOptions += "-feature"
libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "com.chuusai" %% "shapeless" % "2.3.12",
  "org.typelevel" %% "cats-core" % "2.12.0"
)
