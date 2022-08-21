package com.example.todo_app;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class SignUpPageController {

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

    }
}
