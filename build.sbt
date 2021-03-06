name := """muscle-training-manager-api"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  guice,filters,ws,
  "com.google.firebase"    % "firebase-admin"         % "6.8.1",
  "org.json4s"             %% "json4s-native"         % "3.6.9",
  "org.postgresql"         % "postgresql"             % "42.2.18",
  "com.typesafe.play"      %% "play-slick"            % "5.0.0",
  "org.flywaydb"           %% "flyway-play"           % "6.0.0",
  "javax.xml.bind"         % "jaxb-api"               % "2.3.0",
  "org.scalatestplus.play" %% "scalatestplus-play"    % "5.0.0"  % Test
)
