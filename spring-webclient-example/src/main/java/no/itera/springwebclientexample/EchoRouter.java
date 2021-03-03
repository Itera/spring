package no.itera.springwebclientexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class EchoRouter {
  @Bean
  public RouterFunction<ServerResponse> route(EchoHandler handler) {
    return RouterFunctions
        .route(RequestPredicates.GET("/echo")
            .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), handler::echo);
  }

  @Bean
  public RouterFunction<ServerResponse> routeRemote(RemoteEchoHandler handler) {
    return RouterFunctions
        .route(RequestPredicates.GET("/remoteEcho")
            .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), handler::echo);
  }
}
