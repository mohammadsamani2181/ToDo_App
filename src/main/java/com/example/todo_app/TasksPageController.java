package com.example.todo_app;

import com.example.todo_app.database.DBHandler;
import com.example.todo_app.model.Task;
import com.example.todo_app.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TasksPageController {

    private User user;

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    private MFXLegacyListView<Task> tasksPageListView;

    @FXML
    private MFXTextField tasksPageTaskFld;

    @FXML
    private MFXTextField tasksPageDescriptionFld;

    @FXML
    private MFXButton tasksPageAddBtn;

    @FXML
    private Label tasksPageSuccessfullLbl;

    @FXML
    private ImageView tasksPageImageView;

    @FXML
    void initialize() {

        try {

            showTasksForTheFirstEnter();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tasksPageAddBtn.setOnAction(e -> {
            addNewTask();
        });
    }


    private void addNewTask () {
        String task = tasksPageTaskFld.getText().trim();
        String description = tasksPageDescriptionFld.getText().trim();

        DBHandler dbHandler;
        Task newTask;

        if (!task.equals("") && !description.equals("")) {
            dbHandler = new DBHandler();
            newTask = new Task(user.getId(), task, description, new Timestamp(System.currentTimeMillis()));

            try {

                dbHandler.addNewTask(newTask);

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            tasks.add(newTask);

            tasksPageTaskFld.setText("");
            tasksPageDescriptionFld.setText("");

            tasksPageSuccessfullLbl.setVisible(true);
            tasksPageImageView.setVisible(true);
            invisibleLabelAndImage();

        }else {
            System.out.println("you must enter description and task!");
        }
    }



    private void showTasksForTheFirstEnter() throws SQLException, ClassNotFoundException {
        user = LoginPageController.getUser();
        DBHandler dbHandler = new DBHandler();
        ResultSet resultSet = null;

        
       resultSet = dbHandler.getTasksByUserId(user);

        while (resultSet.next()) {
            Task newTask = new Task();

            newTask.setTask(resultSet.getString("task"));
            newTask.setDescription(resultSet.getString("description"));
            newTask.setDateCreated(resultSet.getTimestamp("datecreated"));

            tasks.add(newTask);
        }

        tasksPageListView.setItems(tasks);
        tasksPageListView.setCellFactory(ShowTasksCellController -> new ShowTasksCellController());
    }

    private void invisibleLabelAndImage() {
        PauseTransition visiblePause1 = new PauseTransition(
                Duration.seconds(5)
        );
        visiblePause1.setOnFinished(
                event -> tasksPageImageView.setVisible(false)
        );
        visiblePause1.play();


        PauseTransition visiblePause2 = new PauseTransition(
                Duration.seconds(5)
        );
        visiblePause2.setOnFinished(
                event -> tasksPageSuccessfullLbl.setVisible(false)
        );
        visiblePause2.play();
    }

}
