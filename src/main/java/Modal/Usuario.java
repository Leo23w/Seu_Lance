package Modal;

import javafx.fxml.FXML;

public class Usuario {

    public int codigo;
    public String usuario;
    public String senha;
    public String localizacao;
    public double contaBancaria;
    public String contato;

    public int getCodigo() {return  codigo;}
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    public void setUsuario(String usuario) {this.usuario = usuario;}
    public String getUsuario() {return usuario;}
    public void setLocalizacao(String localizacao){this.localizacao = localizacao;
    }public String getLocalizacao(){
        return localizacao;}

    public void setSenha(String senha) {this.senha = senha;
    }public String getSenha() {
        return senha;
    }
    public void setContaBancaria(Double contaBancaria){this.contaBancaria=contaBancaria;
    }public Double getContaBancaria(){
        return contaBancaria;
    }

    public void setContato(String contato){this.contato=contato;
    }public String getContato(){
        return contato;
    }
}

