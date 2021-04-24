package za.co.shilton.reactivequote.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.shilton.reactivequote.entity.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, UUID> {
}
