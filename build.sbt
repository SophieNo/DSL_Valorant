ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.4"

// Configuration du projet
lazy val root = (project in file("."))
  .settings(
    name := "Projet-Scala",
    scalaVersion := "3.3.4",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % "0.14.5",
      "io.circe" %% "circe-generic" % "0.14.5",
      "io.circe" %% "circe-parser" % "0.14.5",
      "com.typesafe.play" %% "play-json" % "2.10.0"
    )
  )
