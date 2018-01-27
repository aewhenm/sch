name := "sch-server"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.6",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.6" % Test,

  "com.typesafe.akka" %% "akka-stream" % "2.5.6",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.6" % Test,

  "com.typesafe.akka" %% "akka-http" % "10.0.10",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.0.10" % Test,
  "com.typesafe.akka" %% "akka-slf4j"        % "2.5.6",
  "com.typesafe.akka" %% "akka-http-jackson" % "10.0.10",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.10",

  "com.aerospike" % "aerospike-client" % "latest.integration",
  "joda-time" % "joda-time" % "2.9.3",
  "org.json4s"           %% "json4s-native"     % "3.3.0",
  "org.json4s"           %% "json4s-jackson"    % "3.3.0"
)