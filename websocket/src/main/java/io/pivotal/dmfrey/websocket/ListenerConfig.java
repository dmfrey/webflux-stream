package io.pivotal.dmfrey.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class ListenerConfig {

    private final SubscribableChannel subscribableChannel;

    @Bean
    public Consumer<Event> listener() {

        return event -> subscribableChannel.send( MessageBuilder.withPayload( event ).build() );
    }

}
