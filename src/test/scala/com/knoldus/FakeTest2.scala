package com.knoldus

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class FakeTest2 extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http.baseUrl("https://reqres.in/api/") // Here is the root for all relative URLs
  val scene: ScenarioBuilder = scenario("fake test") // A scenario is a chain of requests and pauses
    .exec(http("request_1")
      .get("users?page=2").check(status.not(404)).check(jsonPath("$..id").find(1)
      .is("8")).check(status.not(404))
      .check(jsonPath("$..first_name").find(2).is("Tobias")))
    .pause(1)
    .exec(http("request_2")
      .get("users/2").check(status.is(200)))
    .pause(2)
    .exec(http("request_3")
      .get("users/23").check(status.is(404)))
    .pause(3)
    .exec(http("request_4")
      .get("unknown").check(jsonPath("$..year").find(5).is("2005")))
    .pause(4)
    .exec(http("request_5")
      .get("unknown/2").check(status.is(200)))
    .pause(5)
    .exec(http("request_6")
      .get("unknown/23").check(status.is(404)))
    .pause(1)
    .exec(http("request_6")
      .post("users").check(status.is(201))
      .formParam("/name", "/morpheus")
      .formParam("/job", "/lead"))
    .pause(2)
    .exec(http("request_7")
      .put("users/2").check(status.is(200))
      .formParam("/name", "/morpheus")
      .formParam("/job", "zion resident"))
    .pause(2)
    .exec(http("request_7")
      .patch("users/2").check(status.is(200))
      .formParam("name", "morpheus")
      .formParam("job", "zion resident"))
    .pause(1)
    .exec(http("request_8")
      .delete("users/2").check(status.is(204)))
    .pause(2)
    .exec(http("request_9")
      .post("register").check(status.is(200))
      .formParam("email", "eve.holt@reqres.in")
      .formParam("password", "pistol"))
    .pause(1)
    .exec(http("request_10")
      .post("register").check(status.is(400))
      .formParam("email", "sydney@fife"))
    .pause(2)
    .exec(http("request_11")
      .post("login").check(status.is(200))
      .formParam("email", "eve.holt@reqres.in")
      .formParam("password", "cityslicka"))
    .pause(1)
    .exec(http("request_12")
      .post("login").check(status.is(400))
      .formParam("email", "peter@klaven"))
    .pause(1)
    .exec(http("request_13")
      .get("users?delay=3"))
    .pause(1)


  setUp(scene.inject(atOnceUsers(50)).protocols(httpProtocol))


}
