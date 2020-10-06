ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.12.11"


lazy val shadedJson4sAst = project
  .enablePlugins(JarjarAbramsPlugin)
  .settings(
    name := "shaded-json4s-ast",
    jarjarLibraryDependency := "org.json4s" %% "json4s-ast" % "3.6.6",
    jarjarShadeRules += ShadeRuleBuilder.moveUnder("org.json4s", "shaded")
  )

lazy val shadedJson4sScalaP = project
  .enablePlugins(JarjarAbramsPlugin)
  .settings(
    name := "shaded-json4s-scalap",
    jarjarLibraryDependency := "org.json4s" %% "json4s-scalap" % "3.6.6",
    jarjarShadeRules += ShadeRuleBuilder.moveUnder("org.json4s", "shaded")
  )


lazy val shadedJson4sCore = project
  .enablePlugins(JarjarAbramsPlugin)
  .dependsOn(shadedJson4sAst, shadedJson4sScalaP)
  .settings(
    name := "shaded-json4s-core",
    jarjarLibraryDependency := "org.json4s" %% "json4s-core" % "3.6.6",
    jarjarShadeRules += ShadeRuleBuilder.moveUnder("org.json4s", "shaded")
  )

lazy val shadedJson4sJackson = project
  .enablePlugins(JarjarAbramsPlugin)
  .dependsOn(shadedJson4sCore)
  .settings(
    name := "shaded-json4s-jackson",
    jarjarLibraryDependency := "org.json4s" %% "json4s-jackson" % "3.6.6",
    jarjarShadeRules += ShadeRuleBuilder.moveUnder("org.json4s", "shaded")
  )

lazy val use = project.dependsOn(shadedJson4sJackson)
