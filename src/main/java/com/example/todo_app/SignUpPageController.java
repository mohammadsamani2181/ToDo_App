package com.example.todo_app;

import com.example.todo_app.database.DBHandler;
import com.example.todo_app.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpPageController {
    private DBHandler dbHandler;

    @FXML
    private MFXTextField signUpPageFirstnameFld;

    @FXML
    private MFXTextField signUpPageLastnameFld;

    @FXML
    private MFXTextField signUpPageUsernameFld;

    @FXML
    private MFXPasswordField signUpPagePasswordFld;

    @FXML
    private MFXTextField signUpPageAddressFld;

    @FXML
    private MFXCheckbox signUpPageMaleBox;

    @FXML
    private MFXCheckbox signUpPageFemaleBox;

    @FXML
    private MFXButton signUpPageSignupBtn;

    @FXML
    private ImageView signUpPageBackIcon;

    @FXML
    private Label signUpPageErrorLbl;



    @FXML
    void initialize() {

        signUpPageMaleBox.setOnMouseClicked(e -> {

            if (signUpPageMaleBox.isSelected()) {
                signUpPageFemaleBox.setSelected(false);

            }else {
                signUpPageFemaleBox.setSelected(true);
            }

        });

        signUpPageFemaleBox.setOnMouseClicked(e -> {

            if (signUpPageFemaleBox.isSelected()) {
                signUpPageMaleBox.setSelected(false);

            }else {
                signUpPageMaleBox.setSelected(true);
            }

        });

        signUpPageSignupBtn.setOnAction(e -> {

            signUpUser();

        });

        signUpPageBackIcon.setOnMouseClicked(e -> {

            backToTheLoginPage();

        });
    }

    private void backToTheLoginPage() {
        signUpPageBackIcon.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showLoginPage() {
        signUpPageSignupBtn.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void signUpUser () {
        String firstname = signUpPageFirstnameFld.getText().trim();
        String lastname = signUpPageLastnameFld.getText().trim();
        String address = signUpPageAddressFld.getText().trim();
        String username = signUpPageUsernameFld.getText().trim();
        String password= signUpPagePasswordFld.getText().trim();
        String gender;
        if (signUpPageFemaleBox.isSelected()) {
            gender = signUpPageFemaleBox.getText().trim();
        }else {
            gender = signUpPageMaleBox.getText().trim();
        }

        if (!firstname.equals("") && !lastname.equals("")
                && !address.equals("") && !username.equals("")
                && !password.equals("")) {

            User newUser = new User(firstname, lastname,
                    username, password,
                    address, gender);

            dbHandler = new DBHandler();
            try {
                dbHandler.createNewUser(newUser);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            showLoginPage();

        }else {
            signUpPageErrorLbl.setText("Please enter all the information.");
            MakeInvisible.start(signUpPageErrorLbl);
        }
    }
}
