package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProdutoModalController implements Initializable {

    @FXML
    TextField codigoField;

    @FXML
    TextField produtoField;

    @FXML
    TextField precoField;

    public static Produto produto;

    @FXML
    public void salvar() {
        Produto novoProduto = new Produto();
        novoProduto.codigo = Integer.parseInt(codigoField.getText());
        novoProduto.nome = produtoField.getText();
        novoProduto.preco = Double.parseDouble(precoField.getText());

        produto = novoProduto;

        HelloApplication.closeCurrentWindow();
    }

    @FXML
    public void cancelar() {

        HelloApplication.closeCurrentWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Produto produtoSelecionado = ProdutoModalController.produto;

        if (produtoSelecionado !=                                                      null) {
            codigoField.setText(Integer.toString(produtoSelecionado.codigo));
            produtoField.setText(produtoSelecionado.nome);
            precoField.setText(Double.toString(produtoSelecionado.preco));

        }
    }
}


