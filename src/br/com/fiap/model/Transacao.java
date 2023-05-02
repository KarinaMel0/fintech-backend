package br.com.fiap.model;

import java.util.Calendar;

public class Transacao {
    private String cd_transacao;
    private int valor;
    private String descricao;
    private String tags;
    private Calendar dataTransacao;
    private String tipoTransacao;
    private String IdUsuario;

    Transacao(int valor, String descricao, String tags, Calendar dataTransacao, String tipoTransacao, String tUsuarioIdUsuario, String cdTransacao){
        this.setCd_transacao(cdTransacao); ;
        this.setValor(valor);
        this.setDescricao(descricao);
        this.setTags(tags);
        this.setDataTransacao(dataTransacao);
        this.setTipoTransacao(tipoTransacao);
        setIdUsuario(tUsuarioIdUsuario);
    }

    public Transacao(){}

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Calendar getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Calendar dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.IdUsuario = idUsuario;
    }

    public String getCd_transacao() {
        return cd_transacao;
    }

    public void setCd_transacao(String cd_transacao) {
        this.cd_transacao = cd_transacao;
    }
}
