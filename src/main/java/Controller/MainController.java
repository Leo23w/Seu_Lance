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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    TableView<Produto> tabelaProduto;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("idproduto"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome") );
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tabelaProduto.setEditable(true);

        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            List<Produto> produtos = produtoDAO.getAll();
            tabelaProduto.getItems().addAll(produtos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    public void perfil() throws IOException {
        HelloApplication.setRoot("perfil-view");
    }
    public void produto() throws IOException {
        HelloApplication.setRoot("produto-view");
    }
    @FXML
    public void leilao() throws IOException, SQLException {
        LeilaoModalController.produto = null;
        Produto produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            LeilaoModalController.produto = produtoSelecionado;
            HelloApplication.showModal("leilao-modal-view");

            Produto selecionarProduto = LeilaoModalController.produto;
            // Modal fechado

            produtoSelecionado.setIdproduto(selecionarProduto.idproduto);
            produtoSelecionado.setNome(selecionarProduto.nome);
            produtoSelecionado.setValor(selecionarProduto.valor);
            produtoSelecionado.setQuantidade(selecionarProduto.quantidade);
            produtoSelecionado.setDescricao(selecionarProduto.descricao);
            produtoSelecionado.setLance(selecionarProduto.lance);


            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.editarProduto(produtoSelecionado);

            tabelaProduto.refresh();
        }else {
            exibirAlerta("Selecione um leilao.", Alert.AlertType.WARNING);
        }
    }
    private void exibirAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}

