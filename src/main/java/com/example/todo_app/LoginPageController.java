package com.example.todo_app;


import com.example.todo_app.database.DBHandler;
import com.example.todo_app.model.User;
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    private DBHandler dbHandler;

    @FXML
    private MFXTextField loginPageUsernameFld;

    @FXML
    private MFXPasswordField loginPagePasswordFld;

    @FXML
    private MFXButton loginPageBtn;

    @FXML
    private Label loginPageCreateAccountBtn;

    @FXML
    void initialize() {
        loginPageCreateAccountBtn.setOnMouseClicked(e -> {
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
            stage.showAndWait();

        });

        loginPageBtn.setOnAction(e -> {
            String username = loginPageUsernameFld.getText();
            String password = loginPagePasswordFld.getText();
            User user = new User();
            dbHandler = new DBHandler();

            if (!username.equals("") && !password.equals("") ) {
                user.setUsername(username);
                user.setPassword(password);
                ResultSet resultSet = null;

                try {

                    resultSet = dbHandler.findUser(user);
                    if (!resultSet.equals(null)) {

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
                        stage.showAndWait();

                    }else {
                        System.out.println("this user isn't available please create new account");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }else {
                System.out.println("you must enter username and password!");
            }
        });

    }
}
