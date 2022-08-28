package com.example.todo_app;


import com.example.todo_app.database.DBHandler;
import com.example.todo_app.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    private DBHandler dbHandler;

    private static User user;

    @FXML
    private MFXTextField loginPageUsernameFld;

    @FXML
    private MFXPasswordField loginPagePasswordFld;

    @FXML
    private MFXButton loginPageBtn;

    @FXML
    private Label loginPageCreateAccountBtn;

    @FXML
    private Label loginPageErrorLbl;

    @FXML
    void initialize() {
        loginPageCreateAccountBtn.setOnMouseClicked(e -> {

            showSignUpPage ();

        });

        loginPageBtn.setOnAction(e -> {

            loginUser();

        });

    }

    private void loginUser () {

        String username = loginPageUsernameFld.getText();
        String password = loginPagePasswordFld.getText();
        user = new User();
        dbHandler = new DBHandler();

        if (!username.equals("") && !password.equals("") ) {
            user.setUsername(username);
            user.setPassword(password);
            ResultSet resultSet = null;

            try {

                resultSet = dbHandler.findUser(user);

                if (resultSet.isBeforeFirst()) {

                    while (resultSet.next()) {
                        user.setId(resultSet.getInt("idusers"));
                        user.setFirstname(resultSet.getString("firstname"));
                        user.setLastname(resultSet.getString("lastname"));
                        user.setGender(resultSet.getString("gender"));
                        user.setAddress(resultSet.getString("address"));
                    }

                    loginPageBtn.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("tasksPage.fxml"));
                    loader.load();

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                }else {
                    loginPageErrorLbl.setText("The Username or Password is incorrect. Try again.");
                    MakeInvisible.start(loginPageErrorLbl);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }else {
            loginPageErrorLbl.setText("Please enter Username and Password.");
            MakeInvisible.start(loginPageErrorLbl);
        }
    }

    private void showSignUpPage () {
        loginPageCreateAccountBtn.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("signUpPage.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static User getUser() {
        return user;
    }
}
