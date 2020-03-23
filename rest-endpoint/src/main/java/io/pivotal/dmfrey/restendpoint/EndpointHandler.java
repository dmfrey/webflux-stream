package io.pivotal.dmfrey.restendpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static java.lang.Boolean.TRUE;
import static org.springframework.web.reactive.function.server.ServerResponse.accepted;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;

@Component
@RequiredArgsConstructor
public class EndpointHandler {

    private final StreamBridge streamBridge;

    Mono<ServerResponse> handleEvent( ServerRequest request ) {

        return
                request
                        .bodyToMono( Event.class )
                        .map( event -> MessageBuilder.withPayload( event ).build() )
                        .map( msg -> streamBridge.send( "output-out-0", msg ) )
                        .flatMap( sent -> {

                            if( TRUE.equals( sent ) ) {

                                return accepted().build();

                            } else {

                                return badRequest().build();
                            }

                        });

    }

}
