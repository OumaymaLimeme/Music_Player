package com.mycompany.model;
import com.mpatric.mp3agic.Mp3File;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
public class Song  implements Serializable {
   private String  id;
   private String SongName;
   private String artistName;
   private String duration;
   private String lenght;
   private String albumName;
   private String cover;
   private String path;
  private StringProperty url;
   private Image image;
   transient Mp3File mp3file;
   public Song() {};
   public Song(String url) {
        this.url = new SimpleStringProperty(url);
    }
   

   public Mp3File getMp3file() {
        return mp3file;
    }

    public void setMp3file(Mp3File mp3file) {
        this.mp3file = mp3file;
    }
    
     public Song(String id,String artistName,String SongName, String lenght ,String duration, String albumName, String path){
         this.id=id;
         this.SongName= SongName;
         this.artistName= artistName;
         this.albumName= albumName;
         this.duration= duration;
         this.lenght= lenght;
         this.path= path;
       
       
     }
      public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName=artistName;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
             this.SongName=songName;

    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getAlbumName() {
        return albumName;
    }


    public void setAlbumName(String format) {
        this.albumName=format;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

     
      public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


      /**
     * each song can be added to many playlists that's why each song has an arraylist of playlists
     * @return
     */
     ArrayList<String> playlists = new ArrayList<>();
    public ArrayList<String> getPlaylists() {
        return playlists;
    }

    public void addPlaylist(String playlistName){
        playlists.add(playlistName);
    }
    public void removeAlbum(){
        albumName=null;
    }
    public void removePlaylist(String playlist){
        if(playlist.contains(playlist))
            playlists.remove(playlist);
    }
    
    
}
