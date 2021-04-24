package za.co.shilton.reactivequote.entity;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.shilton.reactivequote.enums.CurrencyEnum;

@NoArgsConstructor
@Data
@Table(name = "quote")
@Entity
public class Quote {

  @Id
  @GeneratedValue
  private UUID id;

  private String referenceNumber;

  private UUID productId;
  private BigDecimal totalAmount;
  private BigDecimal feeAmount;
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  private CurrencyEnum currency;
}
