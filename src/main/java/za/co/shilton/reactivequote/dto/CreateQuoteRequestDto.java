package za.co.shilton.reactivequote.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateQuoteRequestDto {

  private UUID productId;
  private Integer quantity;
}
