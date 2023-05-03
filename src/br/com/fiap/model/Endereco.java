package br.com.fiap.model;

public class Endereco {
    private String idEndereco;
    private Integer cep;
    private String rua;
    private String logradouro;
    private String complemento;
    private Integer numero;
    private String idUsuario;


    public Endereco(String idEndereco, Integer cep, String rua, String logradouro, String complemento, int numero, String idUsuario) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.rua = rua;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
        this.idUsuario = idUsuario;
    }

    public Endereco(){};

    public String getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(String idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
