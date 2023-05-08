package it.polimi.ingsw.GUI;


import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class GUI extends Application {
    private Stage window;
    private Scene basicScene;


    public static void main(String[] args) {
        launch(args);
    }

    private void setup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu2.fxml"));
        basicScene = new Scene(loader.load());
        basicScene.setCursor(new ImageCursor(new Image("/graphics/cursor.png")));
    }

    /**
     * Starts the GUI
     *
     * @param stage the Stage class
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void start(Stage stage) throws IOException {
        setup();
        this.window = stage;
        window.setMinWidth(900);
        window.setMinHeight(600);
        window.setResizable(true);
        run();
    }

    /**
     * Sets the title of the main stage and launches the window.
     */
    private void run() {
        window.setTitle("My Shelfie");
        window.setScene(basicScene);
        window.getIcons().add(new Image("/graphics/icon.png"));
        basicScene.setCursor(new ImageCursor(new Image("/graphics/cursor.png")));
        window.show();
    }

    public void quit() {
        System.out.println("Thanks for playing! See you next time!");
        System.exit(0);
    }
}
