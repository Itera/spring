package com.itera.springwebclientexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class RemoteEchoHandler {
  private final WebClient client;

  public RemoteEchoHandler(@Value("${echo.server.url}") String echoServerUrl) {
    this.client = WebClient.create(echoServerUrl);
  }

  public Mono<ServerResponse> echo(ServerRequest request) {
    return client.get().uri(uriBuilder -> uriBuilder
        .queryParam("val", request.queryParam("val").orElse("No value"))
        .build())
        .retrieve()
        .bodyToMono(String.class)
        .flatMap(body -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromValue(body)));
  }
}
