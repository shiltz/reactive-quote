package za.co.shilton.reactivequote.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import za.co.shilton.reactivequote.dto.AmountDto;
import za.co.shilton.reactivequote.dto.CreateQuoteResponseDto;
import za.co.shilton.reactivequote.entity.Quote;

@Mapper(componentModel = "spring")
public interface QuoteMapper {

  @Named("productAmount")
  static AmountDto quoteToAmountDto(Quote quote) {
    return AmountDto.builder()
        .amount(quote.getAmount())
        .currency(quote.getCurrency().toString())
        .build();
  }

  @Named("totalAmount")
  static AmountDto quoteToTotalAmountDto(Quote quote) {
    return AmountDto.builder()
        .amount(quote.getTotalAmount())
        .currency(quote.getCurrency().toString())
        .build();
  }

  @Named("feesAmount")
  static List<AmountDto> quoteToFeeAmountDto(Quote quote) {
    return List.of(AmountDto.builder()
        .amount(quote.getFeeAmount())
        .currency(quote.getCurrency().toString())
        .build());
  }

  @Mapping(target = "amount", source = ".", qualifiedByName = "productAmount")
  @Mapping(target = "total", source = ".", qualifiedByName = "totalAmount")
  @Mapping(target = "fees", source = ".", qualifiedByName = "feesAmount")
  CreateQuoteResponseDto quoteToCreateQuoteResponseDto(Quote quote);
}
