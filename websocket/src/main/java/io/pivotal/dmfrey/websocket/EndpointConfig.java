package io.pivotal.dmfrey.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@Configuration
@RestController
@RequiredArgsConstructor
public class EndpointConfig {

    private final SubscribableChannel subscribableChannel;

    @GetMapping( value = "/sse", produces = TEXT_EVENT_STREAM_VALUE )
    Flux<Event> events() {

        return Flux.create( sink -> {

            MessageHandler handler = message -> sink.next( (Event) message.getPayload() );
            sink.onCancel( () -> subscribableChannel.unsubscribe( handler ) );
            subscribableChannel.subscribe( handler );

        }, FluxSink.OverflowStrategy.LATEST );
    }

}
