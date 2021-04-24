package za.co.shilton.reactivequote.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import za.co.shilton.reactivequote.entity.Quote;
import za.co.shilton.reactivequote.enums.CurrencyEnum;

@DataJpaTest(properties = {"spring.datasource.data=test-data.sql"})
class QuoteRepositoryTest {

  @Autowired
  private QuoteRepository quoteRepository;

  @Test
  public void testSaveQuote() {
    Quote quote = new Quote();
    quote.setAmount(BigDecimal.ONE);
    quote.setCurrency(CurrencyEnum.ZAR);
    quote.setFeeAmount(BigDecimal.ZERO);
    quote.setTotalAmount(BigDecimal.ONE);
    quote.setProductId(UUID.randomUUID());
    quote.setReferenceNumber("VAS");

    var dbQuote = quoteRepository.save(quote);
    assertEquals(BigDecimal.ONE, dbQuote.getAmount());
    assertEquals(BigDecimal.ZERO, dbQuote.getFeeAmount());
    assertEquals(BigDecimal.ONE, dbQuote.getTotalAmount());
    assertEquals("VAS", dbQuote.getReferenceNumber());
    assertEquals(CurrencyEnum.ZAR, dbQuote.getCurrency());
    assertEquals(UUID.class, dbQuote.getId().getClass());
  }

  @Test
  public void testGetQuote() {
    var dbQuote = quoteRepository.findById(UUID.fromString("eb61bfb0-7f1c-4684-9a03-30c805bc733b")).get();
    assertEquals(new BigDecimal("50.00"), dbQuote.getTotalAmount());
    assertEquals(BigDecimal.valueOf(1.01), dbQuote.getFeeAmount());
    assertEquals(BigDecimal.valueOf(48.99), dbQuote.getAmount());
    assertEquals("VAS123", dbQuote.getReferenceNumber());
    assertEquals(CurrencyEnum.ZAR, dbQuote.getCurrency());
    assertEquals("eb61bfb0-7f1c-4684-9a03-30c805bc733b", dbQuote.getId().toString());
  }

}