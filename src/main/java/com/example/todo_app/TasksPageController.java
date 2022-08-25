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
import javafx.scene.image.Image;
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

        user = LoginPageController.getUser();

        try {
            // add all user tasks to the tasks list

            addTasksToTheTasksList();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tasksPageListView.setItems(tasks);
        tasksPageListView.setCellFactory(ShowTasksCellController -> new ShowTasksCellController());

        tasksPageAddBtn.setOnAction(e -> {

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


//                PauseTransition visiblePause = new PauseTransition(
//                        Duration.seconds(3)
//                );
//                visiblePause.setOnFinished(
//                        event -> tasksPageSuccessfullLbl.setVisible(true)
//                );
//                visiblePause.setOnFinished(
//                          event -> tasksPageImageView
//                );
//                visiblePause.play();
                

                tasksPageTaskFld.setText("");
                tasksPageDescriptionFld.setText("");

            }else {
                System.out.println("you must enter description and task!");
            }
        });
    }

    private void addTasksToTheTasksList () throws SQLException, ClassNotFoundException {
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
    }

    public void setUser(User user) {
        this.user = user;
    }
}
