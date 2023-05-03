package br.com.fiap.dao;

import br.com.fiap.model.Investimento;

import java.util.List;

public interface InvestimentoDAO {
    void insert(Investimento investimento);

    List<Investimento> getAll();
}
