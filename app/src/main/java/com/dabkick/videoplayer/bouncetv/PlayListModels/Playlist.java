
package com.dabkick.videoplayer.bouncetv.PlayListModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playlist {

    @SerializedName("playlistId")
    @Expose
    private String playlistId;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

}
