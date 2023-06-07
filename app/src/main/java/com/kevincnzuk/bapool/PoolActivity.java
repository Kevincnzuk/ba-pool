package com.kevincnzuk.bapool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoolActivity extends AppCompatActivity {

    private static final String TAG = "PoolActivity";

    private int server = 0;
    private String pool = "";
    private int num = 0;

    private MaterialToolbar toolbar;
    private MaterialTextView tvTest;
    private MaterialCardView cardSignature;
    private MaterialButton btnSignature;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);

        initCompounds();

        toolbar.setTitle(R.string.result_title);
        toolbar.setSubtitle(R.string.result_sub_title);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        server = intent.getIntExtra("server", 0);
        pool = intent.getStringExtra("pool");
        num = intent.getIntExtra("num", 0);

        btnSignature.setOnClickListener(v -> {
            btnSignature.setEnabled(false);
            btnSignature.setText(R.string.signature_done);
            cardSignature.setVisibility(View.GONE);

            List<StudentVO> list = randomPool(server, pool, num);

            //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            //manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            //LinearLayoutManager manager = new LinearLayoutManager(this);
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(manager);

            PoolAdapter adapter = new PoolAdapter(PoolActivity.this, list);
            recyclerView.setAdapter(adapter);
        });
    }

    private void initCompounds() {
        toolbar = findViewById(R.id.pool_toolbar);
        tvTest = findViewById(R.id.pool_test_text);
        cardSignature = findViewById(R.id.pool_card_signature);
        btnSignature = findViewById(R.id.pool_button_signature);
        recyclerView = findViewById(R.id.pool_students);

        tvTest.setText(getString(R.string.test,
                IntStatPool.three.length, IntStatPool.two.length, IntStatPool.one.length));
    }

    /**
     * 进行随机抽卡
     * @param uServer 服务器
     * @param uType 池子
     * @param uNum 数量
     */
    private List<StudentVO> randomPool(int uServer, String uType, int uNum) {
        if (uServer == MainActivity.INT && uType.equals("stat")) {

            List<StudentVO> list = new ArrayList<>();

            if (uNum == 1) {
                StudentVO vo = new StudentVO();

                double d = Math.random() * 100;

                if (d < 2.872350) {
                    int i = new Random().nextInt(IntStatPool.three.length);
                    vo.setName(IntStatPool.three[i]);
                    vo.setNameEn(IntStatPool.threeEn[i]);
                    vo.setRank(3);
                } else if (d < 19.380944) {
                    int i = new Random().nextInt(IntStatPool.two.length);
                    vo.setName(IntStatPool.two[i]);
                    vo.setNameEn(IntStatPool.twoEn[i]);
                    vo.setRank(2);
                } else {
                    int i = new Random().nextInt(IntStatPool.one.length);
                    vo.setName(IntStatPool.one[i]);
                    vo.setNameEn(IntStatPool.oneEn[i]);
                    vo.setRank(1);
                }

                list.add(vo);
            } else if (uNum == 10) {
                // 第1-9抽
                for (int j = 0; j < 9; j++) {
                    StudentVO vo = new StudentVO();

                    double d = Math.random() * 100;

                    if (d < 2.872350) {
                        int i = new Random().nextInt(IntStatPool.three.length);
                        vo.setName(IntStatPool.three[i]);
                        vo.setNameEn(IntStatPool.threeEn[i]);
                        vo.setRank(3);
                    } else if (d < 18.499992) {
                        int i = new Random().nextInt(IntStatPool.two.length);
                        vo.setName(IntStatPool.two[i]);
                        vo.setNameEn(IntStatPool.twoEn[i]);
                        vo.setRank(2);
                    } else {
                        int i = new Random().nextInt(IntStatPool.one.length);
                        vo.setName(IntStatPool.one[i]);
                        vo.setNameEn(IntStatPool.oneEn[i]);
                        vo.setRank(1);
                    }

                    list.add(vo);
                }

                // 第10抽
                StudentVO vo = new StudentVO();

                double d = Math.random() * 100;

                if (d < 2.872350) {
                    int i = new Random().nextInt(IntStatPool.three.length);
                    vo.setName(IntStatPool.three[i]);
                    vo.setNameEn(IntStatPool.threeEn[i]);
                    vo.setRank(3);
                } else {
                    int i = new Random().nextInt(IntStatPool.two.length);
                    vo.setName(IntStatPool.two[i]);
                    vo.setNameEn(IntStatPool.twoEn[i]);
                    vo.setRank(2);
                }

                list.add(vo);
            }

            return list;
        }

        return null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}