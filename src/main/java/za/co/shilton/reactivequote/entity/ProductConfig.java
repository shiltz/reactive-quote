package za.co.shilton.reactivequote.entity;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "product_config")
@Entity
public class ProductConfig {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private int id;

  private UUID productId;
  private BigDecimal amount;
}
