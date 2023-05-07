package it.polimi.ingsw.view;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class GuiView extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Main.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 300, 275));
        stage.setTitle("MyShelfie");

        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/graphics/icons/santorini.png")));
        stage.show();
    }
}
