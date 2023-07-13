package Controller;

import Modal.Produto;
import com.example.javafx.HelloApplication;
import Modal.ProdutoDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
    public void novoProduto() throws IOException, SQLException {
        ProdutoModalController.produto = null;

        HelloApplication.showModal("produto-modal-view");

        // O modal foi fechado
        Produto novoProduto = ProdutoModalController.produto;

        if(novoProduto != null) {
            tabelaProduto.getItems().add(novoProduto);

            new ProdutoDAO().novoProduto(novoProduto);
        }
    }

    public void editarProduto () throws IOException, SQLException {
        Produto produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            ProdutoModalController.produto = produtoSelecionado;
            HelloApplication.showModal("produto-modal-view");

            Produto editarProduto = ProdutoModalController.produto;
            // Modal fechado

            produtoSelecionado.setIdproduto(editarProduto.idproduto);
            produtoSelecionado.setNome(editarProduto.nome);
            produtoSelecionado.setValor(editarProduto.valor);
            produtoSelecionado.setQuantidade(editarProduto.quantidade);
            produtoSelecionado.setDescricao(editarProduto.descricao);

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.editarProduto(produtoSelecionado);

            tabelaProduto.refresh();
    }else {
            exibirAlerta("Selecione um produto.", Alert.AlertType.WARNING);
        }
    }
    public  void excluirProduto () throws IOException, SQLException {

        Produto  produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem();
        if(produtoSelecionado != null) {
        //confirmação da exclusão
        Alert  alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir produto");
        alert.setHeaderText(produtoSelecionado.idproduto + " " + produtoSelecionado.nome + ", RS " + produtoSelecionado.valor);
        alert.setContentText("Deseja excluir o produto?");

        Optional<ButtonType> result = alert.showAndWait();
        if ( result.get()== ButtonType.OK) {
            ProdutoDAO excluir = new ProdutoDAO();
            excluir.excluirProduto(produtoSelecionado);
        }


            tabelaProduto.getItems().remove(produtoSelecionado);
    }else {
            HelloApplication.closeCurrentWindow();
        }
    }
    public void voltar() throws IOException {
        HelloApplication.setRoot("main-view");
    }
    private void exibirAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
