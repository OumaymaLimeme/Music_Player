/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.ArrayList;

/**
 *
 * @author oumay
 */
public class album {
      private String albumName;
     ArrayList<Song> albumSongs = new ArrayList<>();

    public album(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public ArrayList<Song> getAlbumSongs() {
        return albumSongs;
    }

    public void setAlbumSongs(ArrayList<Song> albumSongs) {
        this.albumSongs = albumSongs;
    }
    /* ajouter une musique dans l'album arraylist */
    public void removeSong(Song song){
        if(albumSongs!=null && albumSongs.contains(song)){
            albumSongs.remove(song);
        }
    }
    public void addSong(Song song){
            albumSongs.add(song);
    }
}
