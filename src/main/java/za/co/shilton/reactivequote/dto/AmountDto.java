package za.co.shilton.reactivequote.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AmountDto {

  private String currency;
  private BigDecimal amount;
}



