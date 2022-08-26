package com.example.todo_app;

import com.example.todo_app.database.DBHandler;
import com.example.todo_app.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
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
    void initialize() {
        signUpPageSignupBtn.setOnAction(e -> {

            signUpUser();

        });
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

        }else {
            System.out.println("all the information must are entered");
        }
    }
}
