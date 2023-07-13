package Modal;

public class Produto {

    public int idproduto;
    public double lance;

    public String nome;
    public double valor;
    public int quantidade;
    public String descricao;

    public void setLance(double lances){
        this.lance = lances;
    }
    public double getlLances(){return lance;}
    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }
    public int getIdproduto() {return idproduto;}
    public void setNome(String nome) {this.nome = nome;}
    public String getNome() {return nome;}

    public void setValor(double valor) {this.valor = valor; }
    public double getValor() {return valor;}

    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
    public Integer getQuantidade(){return quantidade;}
    public void setDescricao(String descricao) {this.descricao = descricao; }
    public String getDescricao(){return  descricao;}



}
