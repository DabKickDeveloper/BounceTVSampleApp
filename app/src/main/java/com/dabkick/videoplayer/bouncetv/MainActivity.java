package com.dabkick.videoplayer.bouncetv;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dabkick.dkvideoplayer.publicsettings.DabkickRegistration;
import com.dabkick.videoplayer.bouncetv.PlayListModels.PlayListModel;
import com.dabkick.videoplayer.bouncetv.PlayListModels.Playlist;
import com.dabkick.videoplayer.bouncetv.PlayListModels.Video;
import com.dabkick.videoplayer.bouncetv.RetrofitInterface.VideoDetailsInterface;
import com.dabkick.videoplayer.bouncetv.Utils.RetrofitInit;
import com.dabkick.videoplayer.bouncetv.Utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    RecyclerView categoriesList;
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        util = Util.getInstance();
        mDrawerList = findViewById(R.id.navList);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        categoriesList = findViewById(R.id.categories_list);

        DabkickRegistration.newInstance().register(this);

        addDrawerItems();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getPlaylists();

        setupDrawer();
    }


    private void addDrawerItems() {
        String[] osArray = {"Home", "Your Videos", "Your Pictures", "Your Playlists", "", "Settings", "Profile", "Contacts"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    private void setupDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                mDrawerLayout.bringToFront();
                mDrawerList.bringToFront();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                categoriesList.bringToFront();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
        categoriesList.bringToFront();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void getPlaylists() {
        Retrofit retrofit = new RetrofitInit(getApplicationContext()).initializeRetrofit();
        VideoDetailsInterface videoDetailsInterface = retrofit.create(VideoDetailsInterface.class);
        Call<PlayListModel> playListModelCall = videoDetailsInterface.getPlayLists(RetrofitInit.BASE_URL + "videoPlayer/v1/getAppInfo.php?appName=" + getPackageName());
        playListModelCall.enqueue(new Callback<PlayListModel>() {
            @Override
            public void onResponse(Call<PlayListModel> call, Response<PlayListModel> response) {
                if (response.body() != null && response.isSuccessful()) {
                    Util.APP_NAME = response.body().getAppName();
                    util.playLists = response.body().getPlaylists();
                    for (int i = 0; i < response.body().getPlaylists().size(); i++) {
                        util.videosForPlaylists.put(response.body().getPlaylists().get(i).getPlaylistId(), response.body().getPlaylists().get(i).getVideos());
                    }

                }
            }

            @Override
            public void onFailure(Call<PlayListModel> call, Throwable t) {

            }
        });
    }

}
