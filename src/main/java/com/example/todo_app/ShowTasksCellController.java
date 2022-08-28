package com.example.todo_app;

import com.example.todo_app.database.DBHandler;
import com.example.todo_app.model.Task;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

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
                deleteTaskFromDataBase(task);
                deleteTaskFromList();
            });

            tasksCellEditIcon.setOnMouseClicked(e -> {
                Task oldTask = getItem();
                loadAndControlTheEditPage(oldTask);
            });
        }
    }

    private void deleteTaskFromList() {
        getListView().getItems().remove(getItem());
    }

    private void deleteTaskFromDataBase(Task task) {
        DBHandler dbHandler = new DBHandler();

        try {

            dbHandler.deleteTask(task);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAndControlTheEditPage(Task oldTask) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editTaskPage.fxml"));
        DBHandler dbHandler = new DBHandler();

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        EditTaskPageController editTaskPageController = loader.getController();
        MFXTextField editPageTaskFld = editTaskPageController.getEditPageTaskFld();
        MFXTextField editPageDescriptionFld = editTaskPageController.getEditPageDescriptionFld();

        editPageTaskFld.setText(getItem().getTask());
        editPageDescriptionFld.setText(getItem().getDescription());

        editTaskPageController.getEditPageSaveBtn().setOnAction(event -> {
            String task = editPageTaskFld.getText();
            String description = editPageDescriptionFld.getText();

            if (!task.equals("") && !description.equals("")) {
                Task newTask = new Task();
                newTask.setTask(task);
                newTask.setDescription(description);
                newTask.setDateCreated(new Timestamp(System.currentTimeMillis()));

                try {

                    dbHandler.updateTask(oldTask, newTask);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                editPageTaskFld.getScene().getWindow().hide();

            }else {
                System.out.println("you must enter something");
            }
        });

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }


}
