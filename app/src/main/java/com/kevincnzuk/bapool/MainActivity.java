package com.kevincnzuk.bapool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int INT = 0;
    public static final int JAP = 1;

    private MaterialToolbar toolbar;
    private MaterialButton btnStatOnce;
    private MaterialButton btnStatTenth;
    private MaterialButtonToggleGroup toggleGroupServer;

    private int server = INT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCompounds();

        setSupportActionBar(toolbar);

        toStatPool();
    }

    private void initCompounds() {
        toolbar = findViewById(R.id.main_toolbar);

        btnStatOnce = findViewById(R.id.main_stat_once);
        btnStatTenth = findViewById(R.id.main_stat_tenth);

        toggleGroupServer = findViewById(R.id.main_server_toggle_group);

        toggleGroupServer.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (checkedId == R.id.main_server_int) {
                server = INT;
            } else if (checkedId == R.id.main_server_jap) {
                server = JAP;
            }
        });
    }

    private void toStatPool() {
        Intent intent = new Intent(MainActivity.this, PoolActivity.class);
        intent.putExtra("server", server);
        intent.putExtra("pool", "stat");

        btnStatOnce.setOnClickListener(v -> {
            intent.putExtra("num", 1);

            startActivity(intent);
        });

        btnStatTenth.setOnClickListener(v -> {
            intent.putExtra("num", 10);

            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.main_menu_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return true;
    }
}