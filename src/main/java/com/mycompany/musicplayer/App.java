package com.mycompany.musicplayer;
import com.mycompany.model.Song;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {
        private ObservableList<Song> songData = FXCollections.observableArrayList();
      private static Stage primaryStage;
     public App(){}

  @Override
    public void start(Stage primaryStage) throws IOException {
        setStage(primaryStage);
        Scene scene = new Scene(loadFXML("Login"), 1100, 700);
        primaryStage.setScene(scene);
         Image image = new Image("/img/m2.png");
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("eSound");
        primaryStage.show();
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
        
    }
   
    public static Stage getStage() {
        return App.primaryStage;
    }

    private void setStage(Stage stage) {
        App.primaryStage = stage;
    }

}