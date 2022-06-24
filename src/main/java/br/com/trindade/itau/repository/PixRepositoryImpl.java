package br.com.trindade.itau.repository;

import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.domain.entity.PixKeyType;
import br.com.trindade.itau.domain.entity.SearchFilters;
import br.com.trindade.itau.domain.repository.PixRepository;
import br.com.trindade.itau.repository.mongo.PixRepositoryMongo;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PixRepositoryImpl implements PixRepository {

    @Autowired
    private PixRepositoryMongo mongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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

        var docCriterias = new ArrayList<Criteria>();

        var optionalFilters = Optional.ofNullable(filters);

        optionalFilters.map(SearchFilters::getKeyType).map(PixKeyType::getDescription).ifPresent(type ->
               docCriterias.add(new Criteria().andOperator(Criteria.where("keyType").is(type))));

        optionalFilters.map(SearchFilters::getAccount).ifPresent(account ->
                docCriterias.add(new Criteria().andOperator(Criteria.where("accountNumber").is(account))));

        optionalFilters.map(SearchFilters::getAgency).ifPresent(agency ->
                docCriterias.add(new Criteria().andOperator(Criteria.where("agencyNumber").is(agency))));

        optionalFilters.map(SearchFilters::getName).ifPresent(name ->
                docCriterias.add(new Criteria().andOperator(Criteria.where("holderName").is(name))));


        Criteria criteria = new Criteria().andOperator(docCriterias.toArray(new Criteria[docCriterias.size()]));

        var query = new Query(criteria);
         return mongoTemplate.find(query, Pix.class);
    }

    @Override
    public List<Pix> findByTaxId(String taxId) {
        return this.mongoRepository.findAllByTaxId(taxId);
    }
}
