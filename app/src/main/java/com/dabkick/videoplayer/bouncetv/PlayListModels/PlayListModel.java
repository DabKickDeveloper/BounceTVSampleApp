
package com.dabkick.videoplayer.bouncetv.PlayListModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayListModel {

    @SerializedName("appName")
    @Expose
    private String appName;
    @SerializedName("playlists")
    @Expose
    private List<Playlist> playlists = null;
    @SerializedName("backgroundImageUrl")
    @Expose
    private Object backgroundImageUrl;
    @SerializedName("backgroundColorHex")
    @Expose
    private Object backgroundColorHex;
    @SerializedName("primaryColorHex")
    @Expose
    private Object primaryColorHex;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Object getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(Object backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public Object getBackgroundColorHex() {
        return backgroundColorHex;
    }

    public void setBackgroundColorHex(Object backgroundColorHex) {
        this.backgroundColorHex = backgroundColorHex;
    }

    public Object getPrimaryColorHex() {
        return primaryColorHex;
    }

    public void setPrimaryColorHex(Object primaryColorHex) {
        this.primaryColorHex = primaryColorHex;
    }

}
