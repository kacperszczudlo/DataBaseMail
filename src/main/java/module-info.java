module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires java.desktop;
    requires io.github.cdimascio.dotenv.java;

    opens org.example to javafx.fxml;
    exports org.example;
}
