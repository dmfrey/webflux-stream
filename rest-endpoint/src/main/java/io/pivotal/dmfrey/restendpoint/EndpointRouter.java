package io.pivotal.dmfrey.restendpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class EndpointRouter {

    private final EndpointHandler endpointHandler;

    @Bean
    RouterFunction<ServerResponse> routes() {

        return route()
                .path( "/api/request", builder ->  builder
                        .POST( "",
                                accept( APPLICATION_JSON, APPLICATION_XML, APPLICATION_OCTET_STREAM ),
                                endpointHandler::handleEvent
                        )
                )
                .build();
    }

}
