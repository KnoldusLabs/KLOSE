About Gatling Report(nuxeo):-

This is a reporting tool that parses gatling result files(simulation.log) and creates HTML reports with Poly charts or CSV output.

A report is a single standalone html file.

About Nuxeo:-

Nuxeo provides a modular, extensible Java-based open source software platform for enterprise content management and packaged applications for document management, digital asset management and case management. Designed by developers for developers, the Nuxeo platform offers a modern architecture, a powerful plug-in model and extensive packaging capabilities for building content applications.

More information on: http://www.nuxeo.com/

Setup:-

Nuxeo Gatling report is available at MVN Repository (https://mvnrepository.com/artifact/org.nuxeo.tools/gatling-report?repo=nuxeo-public-releases), which contains two versions of it. 4.0 is the latest one(released in oct,2019). The other version is 1.0. Gatling it compatible with the sbt or maven is the hard part, because the dependency just doesn't resolve itself automatically.

What I did was, I added the latest jar and added it as a dependency in the build.sbt or pom.xml, depends on whether you are working with sbt or maven, and specified in the build.sbt itself that where to find the jar in the system, that means I gave the path hard codedly and it may throw some warning when you run "sbt compile" or "mvn compile" but it will not result in a build failure.

You can download the latest jar from here. (https://maven-eu.nuxeo.org/nexus/#nexus-search;quick~gatling-report)


Approach:-

Work doesn't end there, because you would have to configure the version of gatling with the version of jar which you added as a dependency. I am attaching the screenshot of build.sbt and plugins.sbt of my project.

Of course, it is understood that gatling highcharts dependency is already added.
You can find the dependency here

   ***SCREENSHOT build.sbt***


***SCREENSHOT plugins.sbt***




I approached the hit and trial strategy at last, because nothing was getting resolved out on its own. And finally I got a compatible version.

It has been tested successfully with default Gatling 2.3.1, 2.1.7, 3.0.0 and 3.2.1 and 3.3.0 simulation.log format for maven. For sbt, I got this version's compatibility only.

Gatling-report example:-

1) Simulation Report - compact representation of response time for a bench, help to pinpoint slow) requests.

Here's an example https://min.gitcdn.link/cdn/nuxeo/gatling-report/78a7b72c2f5594640d1f49cfc507b545c2804a6c/docs/simulation-1/index.html

2) Differential Report - compare 2 bench results.

Again, here's an example of what it looks like https://min.gitcdn.link/cdn/nuxeo/gatling-report/78a7b72c2f5594640d1f49cfc507b545c2804a6c/docs/diff-sim1-vs-sim2/index.html

3) Trend Report - follow the trend of bench results over time.

Here's an example again, https://min.gitcdn.link/repo/nuxeo/gatling-report/master/docs/trend-sim1-10/index.html

4) Default CSV output - https://github.com/nuxeo/gatling-report/blob/master/docs/sim1-10.csv


Report Generation:-

First, just simply run your gatling test, in case of sbt, follow the steps:-
1- $sbt
2- >gatling:test (for running all your simulation at once)
3- >gatling:testOnly <file name> (for running a single simulation class)

In case of maven,
1- $ mvn gatling:test

-Once your report(s) are generated, follow the commands to generate a CSV output-

$java -jar path/to/gatling-report-VERSION-capsule-fat.jar path/to/simulation.log

-You can also submit multiple simulation files, the output will concatenate stats.

$java -jar path/to/gatling-report-VERSION-capsule-fat.jar path/to/simulation.log [path/to/simulation2.log]

-You can also submit gzipped simulation files:

java -jar path/to/gatling-report-VERSION-capsule-fat.jar path/to/simulation.log.gz


Generate HTML report with Plotly charts:-

When using the "-o" REPORT_PATH option a report is generated.

When submitting a single simulation file it creates a simulation report:

$java -jar path/to/gatling-report-VERSION-capsule-fat.jar path/to/simulation.log.gz -o /path/to/report/directory

When submitting two simulations files it creates a differential report:

$ java -jar path/to/gatling-report-VERSION-capsule-fat.jar path/to/ref/simulation.log.gz path/to/challenger/simuation2.log -o /path/to/report/directory

When you give more than two simulation.log files, it, automatically, will create a trend report.

Note that Poly charts can be edited online, through here https://plot.ly/

More information about nuxeo is on: http://www.nuxeo.com/


