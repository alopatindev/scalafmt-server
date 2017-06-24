name := """scalafmt-server"""
organization := "com.alopatindev"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test
libraryDependencies += "com.geirsson" %% "scalafmt-core" % "1.0.0-RC4"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.alopatindev.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.alopatindev.binders._"
