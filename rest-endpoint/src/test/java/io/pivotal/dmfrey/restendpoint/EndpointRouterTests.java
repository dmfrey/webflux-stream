package io.pivotal.dmfrey.restendpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.accepted;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;

@ExtendWith( SpringExtension.class )
@ContextConfiguration( classes = { EndpointRouter.class })
@WebFluxTest
public class EndpointRouterTests {

    @Autowired
    private ApplicationContext context;

    @MockBean
    EndpointHandler mockEndpointHandler;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {

        webTestClient = WebTestClient.bindToApplicationContext( context ).build();

    }

    @Test
    public void testRequest_verify202Accepted() {

        when( this.mockEndpointHandler.handleEvent( any() ) ).thenReturn( accepted().build() );

        this.webTestClient.post()
                .uri( "/api/request" )
                .accept( APPLICATION_JSON )
                .body( fromValue( "{\"message\": \"this is a test\"}" ) )
                .exchange()
                .expectStatus().isAccepted();

    }

    @Test
    public void testRequest_verify400() {

        when( this.mockEndpointHandler.handleEvent( any() ) ).thenReturn( badRequest().build() );

        this.webTestClient.post()
                .uri( "/api/request" )
                .contentType( TEXT_PLAIN )
                .body( fromValue( "this is a test" ) )
                .exchange()
                .expectStatus().isBadRequest();

    }

}
