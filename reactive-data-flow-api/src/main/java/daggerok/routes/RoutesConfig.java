package daggerok.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RoutesConfig {

  @Bean RouterFunction<ServerResponse> routes(final KafkaOperations<Object, Object> kafka) {

    // send via kafka broker
    return route(POST("/**"),
                 request -> ok().contentType(APPLICATION_JSON)
                                .body(request.bodyToMono(Message.class)
                                             .doOnNext(message -> kafka.send("messages", message.getBody()))
                                             .map(e -> "sending message...")
                                             .flatMap(Mono::just), String.class))
        ;
  }
}
