package za.co.shilton.reactivequote.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.shilton.reactivequote.dto.CreateQuoteRequestDto;
import za.co.shilton.reactivequote.dto.CreateQuoteResponseDto;
import za.co.shilton.reactivequote.entity.Quote;
import za.co.shilton.reactivequote.enums.CurrencyEnum;
import za.co.shilton.reactivequote.mapper.QuoteMapper;
import za.co.shilton.reactivequote.repository.ProductRepository;
import za.co.shilton.reactivequote.repository.QuoteRepository;

@Service
public class QuoteService {

  public static final BigDecimal SERVICE_FEE = BigDecimal.ONE;
  public static final String PRODUCT_NOT_FOUND = "Product not found";

  private final ProductRepository productRepository;
  private final QuoteRepository quoteRepository;
  private final QuoteMapper quoteMapper;

  public QuoteService(ProductRepository productRepository,
                      QuoteRepository quoteRepository,
                      QuoteMapper quoteMapper) {
    this.productRepository = productRepository;
    this.quoteRepository = quoteRepository;
    this.quoteMapper = quoteMapper;
  }

  public Mono<CreateQuoteResponseDto> createQuote(CreateQuoteRequestDto requestDto) {
    return getProductAmount(requestDto)
        .flatMap(amount -> getQuote(requestDto, amount))
        .flatMap(this::saveQuote)
        .flatMap(this::getCreateQuoteResponseDto);
  }

  public Mono<CreateQuoteResponseDto> getQuoteByReferenceNumber(String referenceNumber) {
    var savedQuote =
        this.quoteRepository.findQuoteByReferenceNumber(referenceNumber)
            .orElseThrow(() -> new RuntimeException("Quote not found"));
    return Mono.defer(() -> Mono.just(quoteMapper.quoteToCreateQuoteResponseDto(savedQuote)));
  }

  private Mono<CreateQuoteResponseDto> getCreateQuoteResponseDto(Quote savedQuote) {
    return Mono.defer(() -> Mono.just(quoteMapper.quoteToCreateQuoteResponseDto(savedQuote)));
  }

  private Mono<Quote> saveQuote(Quote quote) {
    return Mono.fromSupplier(() -> quoteRepository.saveAndFlush(quote));
  }

  private Mono<Quote> getQuote(CreateQuoteRequestDto requestDto, BigDecimal productAmount) {
    var quote = new Quote();
    quote.setProductId(requestDto.getProductId());
    quote.setCurrency(CurrencyEnum.ZAR);
    quote.setAmount(productAmount.multiply(BigDecimal.valueOf(requestDto.getQuantity())));
    quote.setFeeAmount(SERVICE_FEE);
    quote.setTotalAmount(quote.getAmount().add(SERVICE_FEE));
    return Mono.just(quote);
  }

  private Mono<BigDecimal> getProductAmount(CreateQuoteRequestDto requestDto) {
    return Mono.fromSupplier(() -> productRepository.findProductConfigByProductIdEquals(requestDto.getProductId())
        .orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND))
        .getAmount());
  }
}
