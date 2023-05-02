package br.com.fiap.model;

public class Cartao {
    private String idCartao;
    private String nomeCartao;
    private String tipoCartao;
    private int valorCartao;
    private String idUsuario;

    public Cartao(
            String idCartao,
            String nomeCartao,
            String tipoCartao,
            int valorCartao,
            String idUsuario
    ) {
        this.setIdCartao(idCartao);
        this.setNomeCartao(nomeCartao);
        this.setTipoCartao(tipoCartao);
        this.setValorCartao(valorCartao);
        this.setIdUsuario(idUsuario);
    }

    public Cartao() {}

    public String getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(String idCartao) {
        this.idCartao = idCartao;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getTipoCartao() {
        return tipoCartao;
    }

    public void setTipoCartao(String tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public int getValorCartao() {
        return valorCartao;
    }

    public void setValorCartao(int valorCartao) {
        this.valorCartao = valorCartao;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
