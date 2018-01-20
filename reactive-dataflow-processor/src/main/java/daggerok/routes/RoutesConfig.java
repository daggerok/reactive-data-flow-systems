package daggerok.routes;

import daggerok.domain.Message;
import daggerok.domain.MessageRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RoutesConfig {

  @Bean RouterFunction<ServerResponse> routes(final MessageRepository messages) {

    // fallback for everything else
    return route(GET("/**"),
                 request -> ok().contentType(APPLICATION_STREAM_JSON)
                                .body(messages.findBy()
                                              .share(),
                                      Message.class))
        ;
  }
}
