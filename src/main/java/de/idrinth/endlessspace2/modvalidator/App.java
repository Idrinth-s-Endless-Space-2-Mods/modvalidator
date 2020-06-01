package de.idrinth.endlessspace2.modvalidator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle(
            "Endless Space 2 Mod Validator | v" + new String(
                getClass().getResourceAsStream("version").readAllBytes()
            )
        );
        App.stage = stage;
        toScene("initial");
    }
    public static void toPrimary() throws IOException {
        toScene("primary");
    }
    private static void toScene(String scene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(scene + ".fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}