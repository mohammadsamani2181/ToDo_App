package com.example.todo_app;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class EditTaskPageController {

    @FXML
    private MFXTextField editPageTaskFld;

    @FXML
    private MFXTextField editPageDescriptionFld;

    @FXML
    private MFXButton editPageSaveBtn;

    @FXML
    void initialize() {

    }

    public MFXTextField getEditPageTaskFld() {
        return editPageTaskFld;
    }

    public MFXTextField getEditPageDescriptionFld() {
        return editPageDescriptionFld;
    }

    public MFXButton getEditPageSaveBtn() {
        return editPageSaveBtn;
    }
}

