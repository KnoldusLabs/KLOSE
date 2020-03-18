name := "gatling-sbt"

version := "0.1"

scalaVersion := "2.12.10"
enablePlugins(GatlingPlugin)

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.0.0" % "test,it"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "3.0.0" % "test,it"
libraryDependencies += "org.nuxeo.tools" % "gatling-report" % "4.0-SNAPSHOT" from
"file:///home/knoldus/gatling-sbt/src/test/resources/gatling-report-4.0-20191010.151839-4-capsule-fat.jar"