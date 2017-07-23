package com.assessment.videoplayerdemo;

import android.content.res.Configuration;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.longtailvideo.jwplayer.JWPlayerView;
import com.longtailvideo.jwplayer.configuration.PlayerConfig;
import com.longtailvideo.jwplayer.events.listeners.VideoPlayerEvents;
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem;

public class PlayerActivity extends AppCompatActivity implements VideoPlayerEvents.OnFullscreenListener{

    private String videoLink;
    private JWPlayerView playerView;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        videoLink = getIntent().getStringExtra(VideoLinks.VIDEOLINK);
        playerView = (JWPlayerView) findViewById(R.id.playerView);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.player_container);
        playerView.addOnFullscreenListener(this);
        PlaylistItem pi = new PlaylistItem.Builder()
                .file(videoLink)
                .title("Video")
                .build();
        playerView.load(pi);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        playerView.setFullscreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE, true);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerView.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (playerView.getFullscreen()) {
                playerView.setFullscreen(false, true);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onFullscreen(boolean b) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (b) {
                actionBar.hide();
            } else {
                actionBar.show();
            }
        }
        coordinatorLayout.setFitsSystemWindows(!b);
    }
}
