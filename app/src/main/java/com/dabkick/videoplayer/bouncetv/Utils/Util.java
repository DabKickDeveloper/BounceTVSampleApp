package com.dabkick.videoplayer.bouncetv.Utils;

import com.dabkick.videoplayer.bouncetv.PlayListModels.Playlist;
import com.dabkick.videoplayer.bouncetv.PlayListModels.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Util {
    public static String APP_NAME = "";

    private static Util instance;
    public List<Playlist> playLists = new ArrayList<>();
    public Map<String, List<Video>> videosForPlaylists = new ConcurrentHashMap<>();


    public static Util getInstance() {

        if (instance == null)
            instance = new Util();
        return instance;
    }

}
