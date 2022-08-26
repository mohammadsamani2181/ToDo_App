package com.example.todo_app;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class EditTaskPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MFXTextField editPageTaskFld;

    @FXML
    private MFXTextField editPageDescriptionFld;

    @FXML
    private MFXButton editPageSaveBtn;

    @FXML
    void initialize() {

    }
}

