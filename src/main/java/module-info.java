module com.example.cs210project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cs210project to javafx.fxml;
    exports com.example.cs210project;
    exports com.example.cs210project.Contoller;
    opens com.example.cs210project.Contoller to javafx.fxml;
    exports com.example.cs210project.View;
    opens com.example.cs210project.View to javafx.fxml;
}