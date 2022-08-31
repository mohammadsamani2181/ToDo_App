module com.example.todo_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires java.sql;


    opens com.example.todo_app to javafx.fxml;
    exports com.example.todo_app;
}