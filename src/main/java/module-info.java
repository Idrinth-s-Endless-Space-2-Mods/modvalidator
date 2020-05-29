module de.idrinth.endlessspace2.modvalidator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    opens de.idrinth.endlessspace2.modvalidator to javafx.fxml;
    exports de.idrinth.endlessspace2.modvalidator;
}