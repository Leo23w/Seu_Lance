package Controller;

import Modal.Usuario;
import com.example.javafx.HelloApplication;
import Modal.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

import java.sql.SQLException;

public class LoginController {
    @FXML
    TextField usuarioField;
    @FXML
    PasswordField senhaField;

    @FXML
    Label labelEntrar;

    @FXML
    Label labelCriar;

    @FXML
    public void entrar() throws IOException, SQLException {
        Usuario usuarioLogin = new Usuario();
        usuarioLogin.usuario = usuarioField.getText();
        usuarioLogin.senha = senhaField.getText();
        boolean usuarioExiste = new UsuarioDAO().existe(usuarioLogin);

        if (usuarioExiste) {
            System.out.println("Entrando...");
            labelEntrar.setText("Entrando...");

            HelloApplication.setRoot("main-view");
        } else {
            System.out.println("Usuário ou senha incorretos");
            labelEntrar.setText("Usuário ou senha incorretos");
        }
    }
    public void criarConta()throws IOException, SQLException{
        PerfilModalController.usuario = null;

        HelloApplication.showModal("perfil-modal-view");

        // O modal foi fechado
        Usuario novousuario = PerfilModalController.usuario;

        if(novousuario != null) {


            new UsuarioDAO().criarUsuario(novousuario);
        }
    }}