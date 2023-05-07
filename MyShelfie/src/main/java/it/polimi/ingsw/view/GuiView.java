package it.polimi.ingsw.view;
//importing javafx libraries
import com.sun.prism.paint.Color;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;



public class GuiView extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();



        stage.show();
    }
}
