package br.com.trindade.itau.repository.mongo;

import br.com.trindade.itau.domain.entity.Pix;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PixRepositoryMongo extends MongoRepository<Pix, String> {

    Optional<Pix> findByKeyValue(String keyValue);
    @Query("{taxId: ?0}")
    List<Pix> findAllByTaxId(String taxId);
}
