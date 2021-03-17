lazy val commonSettings = commonSmlBuildSettings ++ Seq(
  organization := "com.softwaremill.context",
  scalaVersion := "3.0.0-RC1"
)

lazy val rootProject = (project in file("."))
  .settings(commonSettings: _*)
  .settings(publishArtifact := false, name := "scala3-context")
  .aggregate(core)

lazy val core: Project = (project in file("core"))
  .settings(commonSettings: _*)
  .settings(
    name := "core",
    libraryDependencies ++= Seq()
  )
