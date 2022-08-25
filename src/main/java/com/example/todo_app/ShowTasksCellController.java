package com.example.todo_app;

import com.example.todo_app.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ShowTasksCellController extends ListCell<Task> {

    @FXML
    private Label tasksCellDescriptionFld;

    @FXML
    private Label tasksCellTaskFld;

    @FXML
    private Label tasksCellDateCreatedFld;

    @FXML
    private AnchorPane tasksCellRoot;

    private FXMLLoader loader;

    @FXML
    void initialize() {

    }

    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);

        if (task == null || empty) {
            setText(null);
            setGraphic(null);
        }else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("showTasksCell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            tasksCellTaskFld.setText(task.getTask());
            tasksCellDescriptionFld.setText(task.getDescription());
            tasksCellDateCreatedFld.setText(task.getDateCreated().toString());

            setText(null);
            setGraphic(tasksCellRoot);
        }
    }
}
