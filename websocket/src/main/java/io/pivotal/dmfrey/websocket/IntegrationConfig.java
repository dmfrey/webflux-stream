package io.pivotal.dmfrey.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.SubscribableChannel;

@Configuration
public class IntegrationConfig {

    @Bean
    SubscribableChannel subscribableChannel() {

        return MessageChannels.publishSubscribe().get();
    }

}
