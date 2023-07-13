package Controller;

import Modal.Produto;
import Modal.ProdutoDAO;
import com.example.javafx.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LeilaoModalController implements Initializable {
    public static Produto produto;
    @FXML
    TextField lanceField;
    @FXML
    TextField codigoField;

    @FXML
    TextField nomeField;

    @FXML
    TextField valorField;

    @FXML
    TextField quantidadeField;

    @FXML
    TextField descricaoField;

    @FXML
    TableView<Produto> tabelaProduto;
    @FXML
    TableView<Produto> tabelaLances;

    @FXML
    TableColumn<Produto, Integer> colunaCodigo;
    @FXML
    TableColumn<Produto, String> colunaNome;
    @FXML
    TableColumn<Produto, Double> colunaValor;
    @FXML
    TableColumn<Produto, Integer> colunaQuantidade;
    @FXML
    TableColumn<Produto, String> colunaDescricao;
    @FXML
    TableColumn<Produto, Double> colunalance;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Produto produtoSelecionado = LeilaoModalController.produto;

        if (produtoSelecionado != null) {
            codigoField.setText(Integer.toString(produtoSelecionado.idproduto));
            nomeField.setText(produtoSelecionado.nome);

            valorField.setText(Double.toString(produtoSelecionado.valor));
            quantidadeField.setText(Integer.toString(produtoSelecionado.quantidade));
            descricaoField.setText(produtoSelecionado.descricao);
        }

        valorField.textProperty().addListener((o, oldValues, newValue) -> {
            valorField.setText(newValue.replaceAll("[^\\d.]", ""));
        });
        quantidadeField.textProperty().addListener((o, oldValues, newValue) -> {
            quantidadeField.setText(newValue.replaceAll("[^\\d.]", ""));
        });

    }
    @FXML
    public void fazerLance() {
        Produto novoProduto = new Produto();
        produto = novoProduto;

        if (!codigoField.getText().isBlank()) {
            novoProduto.idproduto = Integer.parseInt(codigoField.getText());
        }
        novoProduto.descricao = descricaoField.getText();
        if (!valorField.getText().isBlank()) {
            try {
                novoProduto.valor = Double.parseDouble(valorField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atenção");
                alert.setHeaderText(null);
                alert.setContentText("Preencha corretamente os campos marcados com *");
                alert.showAndWait();
            }
        } if (!quantidadeField.getText().isBlank()) {
            try {
                novoProduto.quantidade = Integer.parseInt(quantidadeField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atenção");
                alert.setHeaderText(null);
                alert.setContentText("Preencha corretamente os campos marcados com *");
                alert.showAndWait();


            }
        }
        novoProduto.nome = nomeField.getText();


        produto = novoProduto;

        if (descricaoField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Preencha corretamente os campos marcados com *");
            alert.showAndWait();


        } else if (nomeField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Preencha corretamente os campos marcados com *");
            alert.showAndWait();


        } else if (valorField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Preencha corretamente os campos marcados com *");
            alert.showAndWait();


        }  else {
            HelloApplication.closeCurrentWindow();
        }
    }



    public void sair(){
        HelloApplication.closeCurrentWindow();

    }

}