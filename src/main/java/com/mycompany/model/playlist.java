package com.mycompany.model;

import java.io.Serializable;
import java.util.ArrayList;
/**
  * chaque chanson peut être ajoutée à de nombreuses listes de lecture afin que chaque chanson ait une liste de listes de lecture
  */
public class playlist implements Serializable  {
     private String playlistName;
     private ArrayList<Song> playlist;

    public playlist(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    
}
