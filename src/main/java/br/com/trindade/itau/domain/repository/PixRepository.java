package br.com.trindade.itau.domain.repository;

import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.domain.entity.PixUpdate;
import br.com.trindade.itau.domain.entity.SearchFilters;

import java.util.List;

public interface PixRepository {

    public Pix create(Pix body);
    public Pix update(String id, PixUpdate body);
    public Pix findById (String id);
    public Pix inactive(String id);
    public Pix findByKeyValue (String value);
    public List<Pix> findByFilters(SearchFilters filters);
    public List<Pix> findByTaxId(String taxId);
}
