package com.example.javafx;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.Objects;

public class MainController {


    @FXML
    public void perfil() throws IOException {
        HelloApplication.setRoot("perfil-view");
    }

}