package com.example.todo_app;


import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private MFXTextField loginPageUsernameFld;

    @FXML
    private MFXPasswordField loginPagePasswordFld;

    @FXML
    private MFXButton loginPageBtn;

    @FXML
    private MFXButton loginPageSignUpBtn;

    @FXML
    private Label loginPageCreateAccountBtn;

    @FXML
    void initialize() {
        loginPageCreateAccountBtn.setOnMouseClicked(e -> {
            loginPageCreateAccountBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("signUpPage.fxml"));
            System.out.println(loader.getLocation());
            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });
    }
}
