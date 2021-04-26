package za.co.shilton.reactivequote.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import za.co.shilton.reactivequote.dto.CreateQuoteRequestDto;
import za.co.shilton.reactivequote.repository.QuoteRepository;

@SpringBootTest
class QuoteServiceTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteRepository quoteRepository;

  @Test
  void createQuote() {
    var quote = quoteService.createQuote(CreateQuoteRequestDto.builder()
        .productId(UUID.fromString("eb61bfb0-7f1c-4684-9a03-30c805bc733c"))
        .quantity(2)
        .build());

    StepVerifier.create(quote)
        .consumeNextWith(createQuoteResponseDto -> {
          assertEquals("VAS 00000000001", createQuoteResponseDto.getReferenceNumber());
          assertEquals(BigDecimal.valueOf(59.98), createQuoteResponseDto.getAmount().getAmount());
          assertEquals(BigDecimal.ONE, createQuoteResponseDto.getFees().get(0).getAmount());
          assertEquals(BigDecimal.valueOf(60.98), createQuoteResponseDto.getTotal().getAmount());
        }).verifyComplete();
  }

}