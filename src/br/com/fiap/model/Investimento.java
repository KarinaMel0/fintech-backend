package br.com.fiap.model;

import java.util.Calendar;

public class Investimento extends Transacao{

    private String nomeInvestimento;

    public Investimento(int valor, String descricao, String tags, Calendar dataTransacao, String tipoTransacao, String tUsuarioIdUsuario, String cdTransacao, String nomeInvestimento){
        super(valor, descricao, tags, dataTransacao, tipoTransacao, tUsuarioIdUsuario, cdTransacao);
        this.nomeInvestimento = nomeInvestimento;

    }

    public Investimento(){}

    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
    }
}
