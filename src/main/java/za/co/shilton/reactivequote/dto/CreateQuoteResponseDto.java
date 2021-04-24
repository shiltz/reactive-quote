package za.co.shilton.reactivequote.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateQuoteResponseDto {

  private String referenceNumber;
  private AmountDto amount;
  private List<AmountDto> fees;
  private AmountDto total;
}
