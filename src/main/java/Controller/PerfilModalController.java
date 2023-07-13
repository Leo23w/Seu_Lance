package Controller;

import Modal.Usuario;
import com.example.javafx.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PerfilModalController implements Initializable {

    @FXML
    TextField usuarioField;
    @FXML
    TextField senhaField;
    @FXML
    TextField codigoField;
    @FXML
    TextField localizacaoField;
    @FXML
    TextField contaBancariaField;
    @FXML
    TextField contatoField;

    public static Usuario usuario;

    @FXML
    public void salvar() {
        Usuario novoUsuario = new Usuario();
        usuario = novoUsuario;

        if (!codigoField.getText().isBlank()) {
            novoUsuario.codigo = Integer.parseInt(codigoField.getText());
        }
        novoUsuario.localizacao = localizacaoField.getText();
        if (!contaBancariaField.getText().isBlank()) {
            try {
                novoUsuario.contaBancaria = Double.parseDouble(contaBancariaField.getText());
            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atenção");
                alert.setHeaderText(null);
                alert.setContentText("Prencha corretamenre os campos marcados com *");
            }
        }
        novoUsuario.usuario = usuarioField.getText();
        novoUsuario.senha = senhaField.getText();
        novoUsuario.contato = contatoField.getText();


        if (localizacaoField.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Prencha corretamenre os campos marcados com *");

            alert.showAndWait();
        } else if (usuarioField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Prencha corretamenre os campos marcados com *");

            alert.showAndWait();
        } else if (senhaField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Prencha corretamenre os campos marcados com *");

            alert.showAndWait();
        } else if (contatoField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Prencha corretamenre os campos marcados com *");

            alert.showAndWait();

        }
        else{
            HelloApplication.closeCurrentWindow();
        }
    }@FXML
    public void cancelar(){
     HelloApplication.closeCurrentWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Usuario usuarioSelecionado = PerfilModalController.usuario;

        if (usuarioSelecionado != null) {
            codigoField.setText(Integer.toString(usuarioSelecionado.codigo));
            localizacaoField.setText(usuarioSelecionado.localizacao);
            contaBancariaField.setText(String.valueOf(usuarioSelecionado.contaBancaria));
            usuarioField.setText(usuarioSelecionado.usuario);
            senhaField.setText(usuarioSelecionado.senha);
            contatoField.setText(usuarioSelecionado.contato);
        }

        contaBancariaField.textProperty().addListener((o, oldValues, newValue) -> {
            contaBancariaField.setText(newValue.replaceAll("[^\\d.]", ""));
        });
    }
}