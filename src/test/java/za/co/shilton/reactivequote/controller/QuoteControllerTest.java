package za.co.shilton.reactivequote.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import za.co.shilton.reactivequote.dto.AmountDto;
import za.co.shilton.reactivequote.dto.CreateQuoteRequestDto;
import za.co.shilton.reactivequote.dto.CreateQuoteResponseDto;
import za.co.shilton.reactivequote.service.QuoteService;

@WebFluxTest(controllers = QuoteController.class)
class QuoteControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private QuoteService quoteService;

  @Test
  void getQuote() {
    webTestClient.get()
        .uri(QuoteController.QUOTE_SERVICE + "/quote/" + "/123")
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody(String.class)
        .isEqualTo("Hello world!123");
  }

  @Test
  void createQuote() {
    when(quoteService.createQuote(any())).thenReturn(
        Mono.just(CreateQuoteResponseDto.builder()
            .amount(AmountDto.builder()
                .amount(BigDecimal.ONE)
                .currency("R")
                .build())
            .referenceNumber(null)
            .build()));

    webTestClient.post()
        .uri(QuoteController.QUOTE_SERVICE + "/quote")
        .bodyValue(new CreateQuoteRequestDto())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("$.referenceNumber")
        .isEqualTo(null)
        .jsonPath("$.amount.currency")
        .isEqualTo("R");
  }

}