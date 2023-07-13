package com.example.javafx;

import Modal.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProdutoModalController {;

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
            novoProduto.idproduto = Integer.parseInt(codigoField.getText());
            novoProduto.nome = produtoField.getText();
            novoProduto.valor = Double.parseDouble(precoField.getText());

            produto = novoProduto;

            HelloApplication.closeCurrentWindow();
        }

        @FXML
        public void cancelar() {

            HelloApplication.closeCurrentWindow();
        }


        public void initialize(URL url, ResourceBundle resourceBundle) {
            Produto produtoSelecionado = ProdutoModalController.produto;

            if (produtoSelecionado !=                                                      null) {
                codigoField.setText(Integer.toString(produtoSelecionado.idproduto));
                produtoField.setText(produtoSelecionado.nome);
                precoField.setText(Double.toString(produtoSelecionado.valor));

            }
        }
    }

