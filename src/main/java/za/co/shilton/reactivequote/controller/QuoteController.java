package za.co.shilton.reactivequote.controller;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import za.co.shilton.reactivequote.dto.AmountDto;
import za.co.shilton.reactivequote.dto.CreateQuoteRequestDto;
import za.co.shilton.reactivequote.dto.CreateQuoteResponseDto;

@RestController
@RequestMapping(QuoteController.QUOTE_SERVICE + "/quote")
public class QuoteController {

  public static final String QUOTE_SERVICE = "/quote-service";

  @GetMapping("/{id}")
  public Mono<String> getQuote(@PathVariable String id) {
    return Mono.defer(() -> Mono.just("Hello world!" + id));
  }

  @PostMapping
  public Mono<CreateQuoteResponseDto> createQuote(@RequestBody CreateQuoteRequestDto quoteRequestDto) {
    return Mono.defer(() -> Mono.just(CreateQuoteResponseDto.builder()
        .amount(AmountDto.builder()
            .currency("R")
            .amount(BigDecimal.ONE)
            .build())
        .build()));
  }
}
