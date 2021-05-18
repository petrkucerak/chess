module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens cz.cvut.fel.pjv to javafx.fxml;
    exports cz.cvut.fel.pjv;
}
