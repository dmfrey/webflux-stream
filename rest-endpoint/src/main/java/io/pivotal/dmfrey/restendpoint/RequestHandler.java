//package io.pivotal.dmfrey.restendpoint;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.function.StreamBridge;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.EmitterProcessor;
//import reactor.core.publisher.Mono;
//
//import static org.springframework.web.reactive.function.server.ServerResponse.accepted;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class RequestHandler {
//
////    private final EmitterProcessor<Message<RestEndpointApplication.Event>> processor;
//    private final StreamBridge streamBridge;
//
//    Mono<ServerResponse> processRequest( final ServerRequest request ) {
//
//        return request.bodyToMono( String.class )
//                .map( RestEndpointApplication.Event::new )
//                .map( e -> {
//
////                    processor.onNext( MessageBuilder.withPayload( e ).build() );
//                    streamBridge.send( "", MessageBuilder.withPayload( e ).build() );
//
//                    return e;
//                })
//                //.doOnEach( payload -> log.info( "processRequest : payload={}", payload ) )
//                .map( e -> accepted().build() );
////                .do( accepted().build() );
//
//    }
//
//}
