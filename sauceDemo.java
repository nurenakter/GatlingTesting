package com.example.mypackage;
  
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class MySimulation extends Simulation {

  HttpProtocolBuilder httpProtocol =
    http.baseUrl("https://www.saucedemo.com")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
      .acceptLanguageHeader("en-US,en;q=0.5")
      .acceptEncodingHeader("gzip, deflate")
      .userAgentHeader(
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/119.0"
      );

  ScenarioBuilder scn = scenario("Test URL scenario")
    .exec(http("Home").get("/inventory.html"));

  {
    setUp(
      scn.injectOpen(stressPeakUsers(95).during(30))
    ).protocols(httpProtocol);
  }
}