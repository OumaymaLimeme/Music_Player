package com.mycompany.musicplayer;
import com.jfoenix.controls.JFXSlider;
import com.mpatric.mp3agic.*;
import com.mycompany.model.Song;
import java.awt.Desktop;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer.Status;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.web.WebEngine;
import java.util.Iterator;
import javafx.event.EventHandler;
import javafx.scene.web.WebView;
public class Controller {
    @FXML
    private AnchorPane window;

    @FXML
    private AnchorPane playlistNode;

    @FXML
    private Pane showPlaylist;
    @FXML
    private Pane imagePane;

    @FXML
    private TableView<Song> songTable;
     @FXML
    private TableColumn<Song, String> idColumn;   
    @FXML
    private TableColumn<Song, String> artistNameColumn;
    @FXML
    private TableColumn<Song, String> songNameColumn;
    @FXML
    private TableColumn<Song, String> durationColumn;
    @FXML
    private TableColumn<Song, String> rateColumn;
    @FXML
    private TableColumn<Song, String> formatColumn;

    @FXML
    private Label artistName;
    @FXML
    private Label albumName;
    @FXML
    private Label songName;
    @FXML
    private Label totalDuration;
    @FXML
    private Label currentDuration;
    @FXML
    private Label volumeValue;
    @FXML
    private Label songsCounter;

    @FXML
    private  JFXSlider songSlider;
    
    @FXML
    private Slider volumeSlider;

    @FXML
    private ImageView folderChooser;

    @FXML
    private ImageView playButton;
    @FXML
    private ImageView pauseButton;
    @FXML
    private ImageView nextSongButton;
    @FXML
    private ImageView previousSongButton;
    @FXML
    private ImageView muteIcon;
    @FXML
    private ImageView volumeIcon;
   @FXML
    private ToggleButton autoPlayIcon;

   // /***************************** de partie de primary controller************************ */
    @FXML
    private Button mediabutton;
    @FXML
//private ObservableList<Song> songData = FXCollections.observableArrayList();   

    void mediabutton(ActionEvent event) throws IOException {
       mediabutton.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("media.fxml"));
                        Scene scene = new Scene(root);
                        Stage st = new Stage();
                        st.setScene(scene);
                        Image image = new Image("/img/m2.png");
                        st.getIcons().add(image);
                        st.setTitle("Media Player Page");
                        st.show();
    }


    @FXML
    private Button youtbutton;

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
    /***********************EnnnnnnnnnnnnnnnnnnnnnnnnnnD *********************/
   @FXML
    private final   Stage stage ;
    public    App main ;
    
    private final List<MediaPlayer> players;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    
    private boolean isAutoplay;
    private double volume=10;
    private String path;
    
    private double xOffset=0;
     private double yOffset=0;
     
     private final FadeTransition fadeIn = new FadeTransition();
     private final FadeTransition fadeOut = new FadeTransition();
     private Iterator<String> songIterator;      

     public Controller() {
         players= new ArrayList<>();
         songSlider= new JFXSlider();
         volume = 0.1;
         isAutoplay=false;
         stage = App.getStage();
     }
      @FXML
        private void initialize() throws Exception {
          window.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
              xOffset = stage.getX() - event.getScreenX();
              yOffset = stage.getY() - event.getScreenY();
          });

        window.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
          });
        autoPlayIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if(isAutoplay) {
                autoPlayIcon.setSelected(false);
                isAutoplay = false;
            }
            else if(!isAutoplay) {
                autoPlayIcon.setSelected(true);
                isAutoplay = true;
            }
          });

       showPlaylist.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
           if(playlistNode.isVisible() == true) {
               hideTransition(playlistNode);
           }
           else {
               showTransition(playlistNode);
           }
          });
          idColumn.setCellValueFactory(cellData ->  new SimpleStringProperty(( cellData.getValue().getId())));
         rateColumn.setCellValueFactory(cellData ->  new SimpleStringProperty(( cellData.getValue().getDuration())));
         songNameColumn.setCellValueFactory(cellData ->  new SimpleStringProperty(( cellData.getValue().getSongName())));
         durationColumn.setCellValueFactory(cellData ->  new SimpleStringProperty(( cellData.getValue().getLenght())));
         formatColumn.setCellValueFactory(cellData ->  new SimpleStringProperty(( cellData.getValue().getAlbumName())));
          artistNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(( cellData.getValue().getArtistName())));
         
        showSongInfo(null);

        songTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showSongInfo(newValue));

          folderChooser.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
              DirectoryChooser chooser = new DirectoryChooser();
              File selectedDirectory = chooser.showDialog(stage);
              if (selectedDirectory == null) {
                  System.out.println("No directory selected!");
              } else {
                  try {
                      if(!(players.isEmpty())) {
                          players.clear();
                          System.out.println("new array list");
                      }
                      songTable.setItems(songsUrls(selectedDirectory));
                      songTable.setOnMouseClicked((MouseEvent ee) -> {
                          if (ee.getClickCount() <= 0 || ee.getClickCount() >= 2) {
                          } else {
                              try {
                                  takeCare();
                              }
                              catch (Exception ex) {};
                          }
                      });
                  } catch (Exception p) {}
              }
          });
    }

    public void showSongInfo(Song song) {
        if(song != null) {
            artistName.setText(song.getArtistName());
            songName.setText(song.getSongName());
            albumName.setText(song.getAlbumName());
        }
        else {
            artistName.setText("-");
            songName.setText("-");
            albumName.setText("-");
        }

    }
        public ObservableList<Song> songsUrls(File dir)   throws Exception{
            
        ObservableList<Song> songData = FXCollections.observableArrayList();
        File[] files = dir.listFiles();
        String name;
        int i = 0;
        for(File file : files) {
            if(file.isFile()) {
                name = file.getName();
                if(name.endsWith("mp3") || name.endsWith("wav")) {
                    try {
                        i++;
                        Mp3File mp3 = new Mp3File(file.getPath());
                        ID3v2 tag = mp3.getId3v2Tag();
                        Song song = new Song(String.valueOf(i), tag.getArtist(), tag.getTitle(), kbToMb(file.length()), secToMin(mp3.getLengthInSeconds()),tag.getAlbum(), file.getAbsolutePath());
                        players.add(createPlayer(file.getAbsolutePath()));
                        songData.add(song);
                    }
                    catch(IOException e) {e.printStackTrace();}
                }
            }
        }
        setImage();
        i = 0;
        System.out.println(players.size());
        songsCounter.setText("");
        songsCounter.setText("Songs: " + players.size());
        return  songData;
    }

    public void playPauseSong(Song song) throws Exception{
        if(song != null) {
            File file = new File(song.getPath());
            String path = file.getAbsolutePath().replace("\\", "/");       
           if((mediaView != null) && (mediaPlayer != null)) {
                volume = mediaView.getMediaPlayer().getVolume();
                mediaView.getMediaPlayer().stop();
                mediaView = null;
                mediaPlayer = null;
            }
              Media media = new Media(new File(path).toURI().toString());

            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.stop();
            mediaPlayer.setAutoPlay(false);

            mediaView = new MediaView(mediaPlayer);
            pauseIcon();
            mediaView = new MediaView(players.get(Integer.parseInt(song.getId()) - 1));

            volumeValue.setText(String.valueOf((int)volumeSlider.getValue()));
            volumeSlider.setValue(volume * 100);
            mediaView.getMediaPlayer().setVolume(volume);
            mediaView.getMediaPlayer().seek(Duration.ZERO);
//            updateSliderPosition(Duration.ZERO);

            updateValues();
            mediaView.mediaPlayerProperty().addListener((ObservableValue<? extends MediaPlayer> observable, MediaPlayer oldValue, MediaPlayer newValue) -> {
                try {
                    setCurrentlyPlayer(newValue);
                    updateValues();
                } catch (IOException | UnsupportedTagException | InvalidDataException e1) {
                }
            });
             playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                 mediaView.getMediaPlayer().play();
                 playIcon();
                 updateValues();
                 for (int i = ((players.indexOf(mediaView.getMediaPlayer())) % players.size()); i < players.size(); i++) {
                     final MediaPlayer player = players.get(i);
                     mediaPlayer = player;
                     final MediaPlayer nextPlayer = players.get((i + 1) % players.size());
                     mediaPlayer.setOnEndOfMedia(() -> {
                         mediaView.getMediaPlayer().stop();
                         mediaView.getMediaPlayer().seek(Duration.ZERO);
                         if(isAutoplay) {
                             mediaView.getMediaPlayer().seek(Duration.ZERO);
                             repeatSongs();
                             return;
                         }
                         mediaPlayer = nextPlayer;
                         mediaView.setMediaPlayer(mediaPlayer);
                         mediaView.getMediaPlayer().seek(Duration.ZERO);
//                         updateSliderPosition(Duration.ZERO);
//  songSlider.setValue(0);
                         updateValues();
                         mediaPlayer.setVolume(volume);
                         mediaPlayer.play();
                         playIcon();
                     });
                     pauseSong();
                 }
            });

              nextSongButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                  seekAndUpdate(players.get(players.indexOf(mediaView.getMediaPlayer())).getTotalDuration());
            });

            previousSongButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                seekAndUpdate(Duration.ZERO);
            });
         
            
             songSlider.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
                   Bounds b1 = songSlider.getLayoutBounds();
                   double mouseX = event.getX();
                   double percent = (((b1.getMinX() + mouseX) * 100) / (b1.getMaxX() - b1.getMinX()));
                   songSlider.setValue((percent) / 100);
                   seekAndUpdate(new Duration(mediaView.getMediaPlayer().getTotalDuration().toMillis() * percent / 100));
                   songSlider.setValueFactory(slider ->
                           Bindings.createStringBinding(
                                   () -> (secToMin((long) mediaView.getMediaPlayer().getCurrentTime().toSeconds())),
                                   songSlider.valueProperty()
                           )
                   );
            });
           mediaPlayer.currentTimeProperty().addListener((ObservableValue<? extends Duration> ov, Duration t, Duration t1) -> {
            songSlider.setValue(t1.toSeconds());
            });
             songSlider.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    mediaPlayer.seek(Duration.seconds(songSlider.getValue()));
                  
                }
                 
             });
                songSlider.setOnMouseDragged((MouseEvent t) -> {
                mediaPlayer.seek(Duration.seconds(songSlider.getValue()));
            });
              mediaPlayer.setOnReady(new Runnable(){
                @Override
                public void run() {
                  Duration total = media.getDuration();
                  songSlider.setMax(total.toSeconds());
                }
                  
              });

        }
        else {
            if(pauseButton.isVisible()) {
                if ((mediaPlayer != null) && (mediaView != null)) {
                    mediaPlayer = mediaView.getMediaPlayer();
                    mediaPlayer.stop();
                    mediaView = null;
                    mediaPlayer = null;
                }
                pauseIcon();
            }
            System.out.println("Song does not exist!");
        }
    }
    
	
     public String kbToMb(long length) {
        Long l = length;
        double d = l.doubleValue();
        DecimalFormat df = new DecimalFormat("#.00");
        String form = df.format((d/1024)/1024);
        return form + "Mb";
    }

    public String secToMin(long sec) {
        Long s = sec;
        String time = null;
        if((s%60) < 10) {
            time = s/60 + ":0" + s%60;
        }
        else {
            time = s/60 + ":" + s%60;
        }
        return time;
    }
   public MediaPlayer createPlayer(String url) {
       url=url.replace("\\", "/");
         final Media media = new Media(new File(url).toURI().toString());
         final MediaPlayer player = new MediaPlayer(media);
        System.out.println("+++++ " + url);
        return player;
    }

    public Media createMedia(String url) {
       url=url.replace("\\", "/");
         final Media media = new Media(new File(url).toURI().toString());
        return media;
    }
    public void playIcon() {
        playButton.setVisible(false);
        playButton.setDisable(true);
        pauseButton.setVisible(true);
        pauseButton.setDisable(false);
        
    }

    public void  pauseIcon() {
        pauseButton.setVisible(false);
        pauseButton.setDisable(true);
        playButton.setVisible(true);
        playButton.setDisable(false);
    }

         public void setCurrentlyPlayer(MediaPlayer player) throws InvalidDataException, IOException, UnsupportedTagException {
        String source = player.getMedia().getSource();
        source = source.replace("/","\\");
        source = source.replaceAll("%20", " ");
        source = source.replaceAll("%5B", "[");
        source = source.replaceAll("%5D", "]");
        source = source.substring(6,source.length());
        System.out.println(source + " +++");
        Mp3File mp3 = new Mp3File(source);
        ID3v2 tag = mp3.getId3v2Tag();
        artistName.setText(tag.getArtist());
        songName.setText(tag.getTitle());
        albumName.setText(tag.getAlbum());
    }
        public void takeCare() throws Exception {
        if(songTable.getSelectionModel().getSelectedItem() != null) {
            Song song = songTable.getSelectionModel().getSelectedItem();
            playPauseSong(song);
        }
        else {
            System.out.println("null");
        }
    }
     private void seekAndUpdate(Duration duration) {
        final MediaPlayer player = players.get(players.indexOf(mediaView.getMediaPlayer()));

        player.seek(duration);
    }

  private void updateValues() {
        Thread thread = new Thread(() -> {
            do {
                Platform.runLater(() -> {
                    final MediaPlayer player = mediaView.getMediaPlayer();
                    if((player.getStatus() != Status.PAUSED) && (player.getStatus() != Status.STOPPED) && (player.getStatus() != Status.READY)) {
                        double tduration = player.getTotalDuration().toSeconds();
                        totalDuration.setText(secToMin((long) tduration));
                        currentDuration.setText(secToMin((long) player.getCurrentTime().toSeconds()));
                 updateSliderPosition(player.getCurrentTime());
                        volumeHandler();
                    }
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    break;
                }
            }
            while(!players.isEmpty());
        });
        thread.start();
    }

    private void updateSliderPosition(Duration currentTime) {
        final MediaPlayer player = mediaView.getMediaPlayer();
        final Duration totalDuration = player.getTotalDuration();
        if((totalDuration == null) || (currentTime == null)) {
            songSlider.setValue(0);
        }
        else {
            songSlider.setValue((currentTime.toMillis() / totalDuration.toMillis()) * 100);
        }
    }
     private void volumeHandler() {
        volumeSlider.valueProperty().addListener((Observable observable) -> {
            mediaView.getMediaPlayer().setVolume(volumeSlider.getValue() / 100);
            volumeValue.setText(String.valueOf((int)volumeSlider.getValue()));
            volume = mediaView.getMediaPlayer().getVolume();
            volumeIconChanger();
        });
    }

    private void volumeIconChanger() {
        if(volumeSlider.getValue() == 0) {
            muteIcon.setVisible(true);
            volumeIcon.setVisible(false);
        }
        else {
            muteIcon.setVisible(false);
            volumeIcon.setVisible(true);
        }
    }

     private void transitionOperation(AnchorPane anchorPane, FadeTransition fadeTransition, boolean isShowing) {
    	fadeTransition.setNode(anchorPane);
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setFromValue(isShowing ? 0.0 : 1.0);
        fadeTransition.setToValue(isShowing ? 1.0 : 0.0);
        anchorPane.setVisible(isShowing);
        fadeTransition.play();
    }

    private void showTransition(AnchorPane anchorPane) {
        transitionOperation(anchorPane, fadeIn, true);
    }

    private void hideTransition(AnchorPane anchorPane) {
        transitionOperation(anchorPane, fadeOut, false);
    }


         private void showTransation(AnchorPane anchorPane) {
        fadeIn.setNode(anchorPane);
        fadeIn.setDuration(Duration.millis(1000));
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        anchorPane.setVisible(true);
        fadeIn.play();
    }
         private void hideTransation(AnchorPane anchorPane) {
        fadeOut.setNode(anchorPane);
        fadeOut.setDuration(Duration.millis(1000));
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        anchorPane.setVisible(false);
        fadeOut.play();
    }

    private void setImage() throws Exception {
        String url = "";
        url = url.replace("\\", "/").replace(" ", "%20");
        path = "file:/" + path;
        url = ClassLoader.getSystemResource("imag3/hexwave.gif").toExternalForm();
        System.out.println(url);

        imagePane.setStyle("-fx-background-image: url(\"" + url + "\"); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

    }

     private void repeatSongs(){
        mediaView.getMediaPlayer().setOnRepeat(() -> {
            mediaView.getMediaPlayer().seek(Duration.ZERO);
        });
        if(isAutoplay) {
            mediaView.getMediaPlayer().play();
            playIcon();
        }
        else return;
    }
     private void pauseSong() {
        mediaView.getMediaPlayer().setAutoPlay(true);
        pauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (mediaView.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
                mediaView.getMediaPlayer().pause();
                pauseButton.setVisible(false);
                pauseButton.setDisable(true);
                playButton.setVisible(true);
                playButton.setDisable(false);
            }
        });
     }
    public void setMain(App main) {
        this.main = main;
    }

}