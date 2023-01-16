package com.mycompany.musicplayer;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author oumay
 */
public class MediaController implements Initializable {
    @FXML
    private MediaView mediaView;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button resetButton;
     @FXML
    private Slider progressBar;
     @FXML
     private Slider volumeSlider;
   
     @FXML
    private Button backhome;
    
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    
    @FXML
    private Button songbtn;

    @FXML
    private Button youtbutton;
    @FXML
    private Button back;
    // Cette méthode pour accéder à l'interface MP3
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
    void pauseMedia(ActionEvent event) {
          mediaPlayer.pause();
    }

    @FXML
    void playMedia(ActionEvent event) {
            mediaPlayer.play();
    }

    @FXML
    void resetMedia(ActionEvent event) {
            if(mediaPlayer.getStatus()!=MediaPlayer.Status.READY){
               mediaPlayer.seek(javafx.util.Duration.seconds(0.0));
            }
           
    }
    @FXML
    void handelPlayButton(MouseEvent event) {
             System.out.println("playing");
    }
  @FXML
    void handlePauseButton(MouseEvent event) {
           System.out.println("Pause");
    }
        @FXML
    void handleForwardButton(MouseEvent event) {
         System.out.println("next");
    }

    @FXML
    void handleBackwardButton(MouseEvent event) {
         System.out.println("back");
    }
    
    @FXML
    void mute(MouseEvent event) {

    }
    @FXML
    void immute(MouseEvent event) {

    }

     @FXML
    void handleFileLoadButton(MouseEvent event) {

    
    }
     // Cette méthode pour accéder aux youtube directement 
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
  private static Stage appStage;
  
     @FXML
    void backhome(ActionEvent event) throws IOException {
             Parent homepage =FXMLLoader.load(getClass().getResource("primary.fxml"));
             Scene scene =new Scene(homepage);
              appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
             appStage.setScene(scene);  
             appStage.show();
    }

    private String path;
   @FXML
    private void ChooseFile(javafx.event.ActionEvent event) {
        FileChooser fc =new FileChooser();
        File f=fc.showOpenDialog(null);
         path=f.toURI().toString();
        if(path!=null){
            Media media =  new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);         
             mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> ov, Duration t, Duration t1) -> {
                 progressBar.setValue(t1.toSeconds());
            });
             progressBar.setOnMousePressed((MouseEvent t) -> {
                 mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
            });
                 progressBar.setOnMouseDragged((MouseEvent t) -> {
                     mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
            });
              mediaPlayer.setOnReady(() -> {
                  Duration total = media.getDuration();
                  progressBar.setMax(total.toSeconds());
            });
              volumeSlider.setValue(mediaPlayer.getVolume()*100);
              volumeSlider.valueProperty().addListener((javafx.beans.Observable o) -> {
                  mediaPlayer.setVolume(volumeSlider.getValue()/100);
            });
              
            
          mediaPlayer.play();
        }
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }  
    
    
}
