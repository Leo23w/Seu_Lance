package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProdutoController implements Initializable {

    @FXML
    TableView<Produto> tabelaProduto;

    @FXML
    TableColumn<Produto, Integer> colunaCodigo;

    @FXML
    TableColumn<Produto, String> colunaNome;

    @FXML
    TableColumn<Produto, Double> colunaPreco;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome") );
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("senha                                                                       "));

        Produto carro = new Produto();
        carro.codigo = 1;
        carro.nome = "celta";
        carro.preco = 10000;

        Produto moto = new Produto();
        moto.codigo = 1;
        moto.nome = "R2";
        moto.preco = 2000;

       Produto celular = new Produto();
       celular.codigo = 1;
       celular.nome = "Nokia";
       celular.preco = 100;

       tabelaProduto.getItems().add(carro);
       tabelaProduto.getItems().add(moto);
       tabelaProduto.getItems().add(celular);


    }
  @FXML
    public void novo() throws IOException {

        ProdutoModalController.produto = null;

        HelloApplication.showModal("produto-modal-view");

        // O modal foi fechado
      Produto novoProduto = ProdutoModalController.produto;

      if (novoProduto != null) {

          tabelaProduto.getItems().add(novoProduto);
      }
    }
    public void editar () throws IOException {
        Produto  produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();

        ProdutoModalController.produto = produtoSelecionado;

        HelloApplication.showModal("produto-modal-view");

        Produto produtoEditado = ProdutoModalController.produto;

        produtoSelecionado.codigo = produtoEditado.codigo;
        produtoSelecionado.nome = produtoEditado.nome;
        produtoSelecionado.preco = produtoEditado.preco;

        tabelaProduto.refresh();
    }
    public  void excluir () throws IOException {

        Produto  produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();

        //confirmação da exclusão
        Alert  alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir produto");
        alert.setHeaderText(produtoSelecionado.codigo + " " + produtoSelecionado.nome + ", RS " + produtoSelecionado.preco);
        alert.setContentText("Deseja excluir o produto?");

        Optional<ButtonType> result = alert.showAndWait();
        if ( result.get()== ButtonType.OK)

        // Excluir o produto
        tabelaProduto.getItems().remove(produtoSelecionado);
    }
}
