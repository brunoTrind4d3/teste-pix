package br.com.trindade.itau.repository;

import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.domain.entity.SearchFilters;
import br.com.trindade.itau.domain.repository.PixRepository;
import br.com.trindade.itau.repository.mongo.PixRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class PixRepositoryImpl implements PixRepository {

    @Autowired
    private PixRepositoryMongo mongoRepository;

    @Override
    public Pix create(Pix body) {
        if (body.getId() == null) {
            body.setId(UUID.randomUUID().toString());
            body.setActive(true);
            body.setCreatedAt(new Date());
        }
        return this.mongoRepository.save(body);
    }

    @Override
    public Pix update(Pix body) {
        if (!body.isActive()) {
            return null;
        }
        return this.mongoRepository.save(body);
    }

    @Override
    public Pix findById(String id) {
        return this.mongoRepository.findById(id).orElse(null);
    }

    @Override
    public Pix inactive(String id) {
        var pix = this.findById(id);
        if (pix != null) {
            pix.setActive(false);
            pix.setUpdatedAt(new Date());
            return this.update(pix);
        }
        return null;
    }

    @Override
    public Pix findByKeyValue(String value) {
        return this.mongoRepository.findByKeyValue(value).orElse(null);
    }

    @Override
    public List<Pix> findByFilters(SearchFilters filters) {
        return null;
    }

    @Override
    public List<Pix> findByTaxId(String taxId) {
        return this.mongoRepository.findAllByTaxId(taxId);
    }
}
