package com.example.todo_app;

import com.example.todo_app.database.DBHandler;
import com.example.todo_app.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class ShowTasksCellController extends ListCell<Task> {

    @FXML
    private Label tasksCellDescriptionFld;

    @FXML
    private Label tasksCellTaskFld;

    @FXML
    private Label tasksCellDateCreatedFld;

    @FXML
    private AnchorPane tasksCellRoot;

    @FXML
    private ImageView tasksCellDeleteIcon;

    @FXML
    private ImageView tasksCellEditIcon;

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


            tasksCellDeleteIcon.setOnMouseClicked(e -> {
                deletTaskFromDataBase(task);
                deletTaskFromLsit();
            });
        }
    }

    private void deletTaskFromLsit () {
        getListView().getItems().remove(getItem());
    }

    private void deletTaskFromDataBase (Task task) {
        DBHandler dbHandler = new DBHandler();

        try {

            dbHandler.deleteTask(task);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
