package com.mycompany.musicplayer;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import com.mycompany.model.Song;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;

public class PrimaryController implements Initializable {

@FXML
    private HBox favouritecontainer;

    @FXML
    private Button media;

    @FXML
    private HBox recentlyplayedcontainer;

    @FXML
    private Pane song;

    @FXML
    private Button songbtn;

    @FXML
    private Button youtbutton;
      
    @FXML
    private ImageView pause;
    @FXML
    private ImageView play;
    @FXML
   //private ObservableList<Song> songData = FXCollections.observableArrayList();   

    void media(ActionEvent event) throws IOException {
       media.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("media.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        Image image = new Image("/img/m2.png");
                        stage.getIcons().add(image);
                        stage.setTitle("Media Player Page");
                        stage.show();
    }

    @FXML
    void songbtn (ActionEvent event) throws IOException {
         songbtn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("mp3Player.fxml"));
                        Scene scene = new Scene(root);
                       scene.setFill(Color.TRANSPARENT);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        Image image = new Image("/img/m2.png");
                        stage.getIcons().add(image);
                        stage.setTitle("Mp3 Player Page");
                        stage.show();
    }
    
 
    @FXML
    private WebView youtube;
    private WebEngine e;
    @FXML
    void youtbutton(MouseEvent event) throws URISyntaxException, IOException{
        Desktop.getDesktop().browse(new URI("https://www.youtube.com/"));
    }
    
    @FXML
    private void close_app(MouseEvent event) throws IOException {
      System.exit(0);
   }
    @FXML
    private void minimize_app(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    void home(MouseEvent event) {
        JOptionPane.showMessageDialog(null," Welcome This is Home ");
        
    }
   
    List<Song> recentlyPlayed ;
    List<Song> favourites;
    private MediaPlayer mediaplayer ;
    private MediaView mediaView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recentlyPlayed = new ArrayList<>(getRecentlyPlayed());
        try {
            for(Song song : recentlyPlayed ){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("song.fxml"));
                 VBox vBox = fxmlLoader.load();
                SongController songController = fxmlLoader.getController();
                songController.setData(song);
                recentlyplayedcontainer.getChildren().add(vBox);
            }
            
        }catch(IOException e){  e.printStackTrace();   }
        favourites = new ArrayList<>(getFavourites());
          try {
              for(Song song : favourites){
                  FXMLLoader fxmlLoader = new FXMLLoader();
                  fxmlLoader.setLocation(getClass().getResource("song.fxml"));
                   VBox vBox = fxmlLoader.load();
                  SongController songController = fxmlLoader.getController();
                  songController.setData(song);
                  favouritecontainer.getChildren().add(vBox);
                  
              }
          }catch(IOException e ){
              e.printStackTrace();
          }
    }
    private List<Song> getRecentlyPlayed(){
        List<Song> ls = new ArrayList<>();
        Song song = new Song();
        song.setSongName("shape of you");
        song.setArtistName("ED SHERREN");
        song.setCover("/img/shapeofyou.jpg");
        song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/Shapeof You.mp3");
        
        ls.add(song);
        
        song = new Song();
        song.setSongName("Who says");
        song.setArtistName("Selena Gomez");
        song.setCover("/img/For_You.png");
        song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/Who Says.mp3");
        ls.add(song);
        
        song = new Song();
        song.setSongName("On My Way");
        song.setArtistName("Alan Walker");
        song.setCover("/img/onmyway.jpg");
       song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/OnMyWay.mp3.mp3");
        ls.add(song);
        
        song = new Song();
        song.setSongName(" قدام الكل");
        song.setArtistName("Silawy");
        song.setCover("/img/silawy.jpg");
      song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/Siilawy.mp3");
        ls.add(song);
        
        song = new Song();
        song.setSongName("Set Fire To The Rain");
        song.setArtistName("Adel");
        song.setCover("/img/adel.jpg");
    song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/SetFireToTheRain.mp3");
        ls.add(song);
        
        song = new Song();
        song.setSongName("Radioactive");
        song.setArtistName("Imagine Dragon");
        song.setCover("/img/imagine.jpeg");
       song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/Radioactive.mp3");
        ls.add(song);
        
        return ls;
    }
    private List<Song> getFavourites(){
         List<Song> ls = new ArrayList<>();
        Song song = new Song();
        
        song.setSongName("Butter");
        song.setArtistName("BTS");
        song.setCover("/images/butter.png");
       song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/Butter.mp3");
        ls.add(song);
        
         song = new Song();
        song.setSongName("No Tears Left To Cry");
        song.setArtistName("Ariana Grand");
        song.setCover("/images/no tears left to cry.png");
     song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/No Tears Left to Cry.mp3");
        ls.add(song);
        
        song = new Song();
        song.setSongName("New Rules");
        song.setArtistName("Dua Lipa");
        song.setCover("/images/dua.png");
        song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/New Rules.mp3");
        ls.add(song);
        
        song = new Song();
        song.setSongName("Photograph");
        song.setArtistName("ED SHEREEN");
        song.setCover("/images/photo.jpg");
      song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/Photograph.mp3");
        ls.add(song);
        
        song = new Song();
        song.setSongName("Try");
        song.setArtistName("Pink");
        song.setCover("/images/pink.jpg");
        song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/Try.mp3");
        ls.add(song);
        
        song = new Song();
        song.setSongName("Evolution of Arabic Music | تطور الموسيقى العربية");
        song.setArtistName("Alaa Wardi");
        song.setCover("/images/alaa.png");
         song.setPath("C:/Users/oumay/OneDrive/Bureau/Songs/EvolutionofArabicMusic.mp3");
        ls.add(song);
        
        return ls;
    }
   
        
    private Stage myStage;
    public void setStage(Stage stage) {
     myStage = stage;
}
       
}
