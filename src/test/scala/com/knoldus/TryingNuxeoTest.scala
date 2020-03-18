package com.knoldus

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class TryingNuxeoTest extends Simulation {
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://dummy.restapiexample.com/api/v1/employee")

  val scenarioBuilder: ScenarioBuilder =
    scenario("rest_Api_example")
      .exec(http("nux")
        .get("http://dummy.restapiexample.com/api/v1/employees").check(status.not(404))
        .check(jsonPath("$..employee_name").find(18).is("Bradley Greer")))
  setUp(scenarioBuilder.inject(atOnceUsers(20)).protocols(httpProtocol))


}