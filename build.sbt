import NativePackagerHelper._

name := """scalafmt-server"""
organization := "io.github.alopatindev"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test
libraryDependencies += "com.geirsson" %% "scalafmt-core" % "1.0.0-RC4"

javaOptions in Universal ++= Seq(
  "-J-Xms512m",
  "-J-Xmn512m",
  "-Dhttp.address=localhost"
)

mappings in Universal ++= contentOf(baseDirectory.value / "scripts")
mappings in Universal ++= Seq("LICENSE.txt").map { filename => file(filename) -> filename }
