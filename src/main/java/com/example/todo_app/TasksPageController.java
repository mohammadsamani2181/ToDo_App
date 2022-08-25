package com.example.todo_app;

import com.example.todo_app.database.DBHandler;
import com.example.todo_app.model.Task;
import com.example.todo_app.model.User;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


import java.sql.SQLException;
import java.sql.Timestamp;

public class TasksPageController {

    private User user;

    @FXML
    private MFXLegacyListView<?> tasksPageListView;

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

    public void setUser(User user) {
        this.user = user;
    }
}
