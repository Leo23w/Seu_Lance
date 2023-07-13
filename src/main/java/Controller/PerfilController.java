package Controller;

import Modal.Usuario;
import Modal.UsuarioDAO;
import com.example.javafx.HelloApplication;
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

public class PerfilController implements Initializable {

    @FXML
    private TableView<Usuario> tabelaUsuario;

    @FXML
    private TableColumn<Usuario, Integer> colunaCodigo;

    @FXML
    private TableColumn<Usuario, String> colunaUsuario;

    @FXML
    private TableColumn<Usuario, String> colunaSenha;
    @FXML
    private TableColumn<Usuario, String> colunaLocalizacao;
    @FXML
    private TableColumn<Usuario, Double> colunaContaBancaria;
    @FXML
    private TableColumn<Usuario, String> colunaContato;

    @FXML
    private TextField usuarioField;

    @FXML
    private TextField senhaField;

    @FXML
    private TextField localizacaoField;

    @FXML
    private TextField contaBancariaField;

    @FXML
    private TextField contatoField;



    public void editaUsuario() throws IOException, SQLException {
        Usuario usuarioSelecionado = tabelaUsuario.getSelectionModel().getSelectedItem();
        if (usuarioSelecionado != null) {
            PerfilModalController.usuario = usuarioSelecionado;
            HelloApplication.showModal("perfil-modal-view");

            Usuario editarUsuario = PerfilModalController.usuario;
            // Modal fechado

            usuarioSelecionado.setLocalizacao(editarUsuario.getLocalizacao());
            usuarioSelecionado.setContaBancaria(editarUsuario.getContaBancaria());
            usuarioSelecionado.setUsuario(editarUsuario.getUsuario());
            usuarioSelecionado.setSenha(editarUsuario.getSenha());
            usuarioSelecionado.setContato(editarUsuario.getContato());

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.editarUsuario(usuarioSelecionado);

            tabelaUsuario.refresh();
        } else {
            exibirAlerta("Selecione um usuário.", Alert.AlertType.WARNING);
        }
    }
    @FXML
    public void excluirUsuario() throws IOException, SQLException {
        Usuario usuarioSelecionado = tabelaUsuario.getSelectionModel().getSelectedItem();
        if(usuarioSelecionado != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Excluir usuario");
            alert.setHeaderText(usuarioSelecionado.codigo + ", Usuario: " + usuarioSelecionado.usuario + ", Senha: " + usuarioSelecionado.senha);
            alert.setContentText("Deseja excluir o usuario?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                UsuarioDAO delete = new UsuarioDAO();
                delete.excluirUsuario(usuarioSelecionado);

                tabelaUsuario.getItems().remove(usuarioSelecionado);
            }
        }else {
            exibirAlerta("Selecione um usuário.", Alert.AlertType.WARNING);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        colunaContaBancaria.setCellValueFactory(new PropertyValueFactory<>("contaBancaria"));
        colunaUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colunaSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        colunaContato.setCellValueFactory(new PropertyValueFactory<>("contato"));
        tabelaUsuario.setEditable(true);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            List<Usuario> usuarios = usuarioDAO.getAll();
            tabelaUsuario.getItems().addAll(usuarios);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

