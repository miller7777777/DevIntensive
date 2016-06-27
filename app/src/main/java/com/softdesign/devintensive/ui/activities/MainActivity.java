package com.softdesign.devintensive.ui.activities;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.softdesign.devintensive.R;
import com.softdesign.devintensive.utils.ConstantManager;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";
    private ImageView mCallImg;
    private CoordinatorLayout mCoordinatorLayout;


    /**
     * Метод вызывается при создании активити (после изменения конфигурации/возврата к
     * текущей активности после ее уничтожения.
     * <p/>
     * В данном месте инициализируется/производится:
     * - UI пользовательский интерфейс (статика)
     * - инициализация статических данных активити
     * - связь данных со списками (инициализация адаптеров)
     * <p/>
     * Не запускать длительные операции по работе с данными в onCreate()!
     *
     * @param savedInstanceState - объект со значением, сохраненным в Bundle - состояние UI
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "OnCreate");

        mCallImg = (ImageView) findViewById(R.id.call_img);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator_container);
        mCallImg.setOnClickListener(this);

        if (savedInstanceState == null){
            // активити запускается впервые

        }else {
            // активити уже создавалось

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "OnRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy");

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.call_img:
//                showProgress();
//                runWithDelay();
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void showSnackbar(String message){
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }
}
