package com.softdesign.devintensive.ui.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.softdesign.devintensive.R;
import com.softdesign.devintensive.data.managers.DataManager;
import com.softdesign.devintensive.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";

    private DataManager mDataManager;
    private int mCurrentEditMode;
    private ImageView mCallImg;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private DrawerLayout mNavigationDrawer;
    private FloatingActionButton mFab;
    private EditText mUserPhone, mUserMail, mUserVk, mUserGit, mUserBio;
    private List<EditText> mUserInfoViews;
    private RelativeLayout mProfilePlaceholder;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private AppBarLayout.LayoutParams mAppBarParams = null;
    private AppBarLayout mAppBarLayout;


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

        mDataManager = DataManager.getInstance();

        mCallImg = (ImageView) findViewById(R.id.call_img);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator_container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mCallImg.setOnClickListener(this);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mProfilePlaceholder = (RelativeLayout) findViewById(R.id.profile_placeholder);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mUserPhone = (EditText) findViewById(R.id.phone_et);
        mUserMail = (EditText) findViewById(R.id.email_et);
        mUserVk = (EditText) findViewById(R.id.vk_et);
        mUserGit = (EditText) findViewById(R.id.github_et);
        mUserBio = (EditText) findViewById(R.id.bio_et);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);


        mUserInfoViews = new ArrayList<>();
        mUserInfoViews.add(mUserPhone);
        mUserInfoViews.add(mUserMail);
        mUserInfoViews.add(mUserVk);
        mUserInfoViews.add(mUserGit);
        mUserInfoViews.add(mUserBio);
        mFab.setOnClickListener(this);
        mProfilePlaceholder.setOnClickListener(this);
        setupToolbar();
        setupDrawer();
        loadUserInfoValue();




        if (savedInstanceState == null){
            // активити запускается впервые


        }else {
            // активити уже создавалось
            mCurrentEditMode = savedInstanceState.getInt(ConstantManager.EDIT_MODE_KEY, 0);
            changeEditMode(mCurrentEditMode);

        }


    }

    private void setupDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                showSnackbar(item.getTitle().toString());
                item.setChecked(true);
                mNavigationDrawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    /*
     * Получение результата из другой Activity (фото из камеры или галлереи)
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
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
//        saveUserInfoValue();

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
    protected Dialog onCreateDialog(int id) {
//        return super.onCreateDialog(id);
        switch (id){
            case ConstantManager.LOAD_PROFILE_PHOTO:
                String [] selectItems = {getString(R.string.user_profile_dialog_gallery), getString(R.string.user_profile_dialog_camera), getString(R.string.user_profile_dialog_cancel)};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.user_profile_dialog_title));
                builder.setItems(selectItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choiceItem) {
                        switch (choiceItem){
                            case 0:
                                //// TODO: 03.07.2016 Загрузить из галлереи
                                showSnackbar("Загрузить из галлереи");
                                break;
                            case 1:
                                //// TODO: 03.07.2016 Загрузить с камеры
                                showSnackbar("Загрузить с камеры");

                                break;
                            case 2:
                                //// TODO: 03.07.2016 Отмена
                                showSnackbar("Отмена");

                                break;
                        }
                    }
                });
                return builder.create();
            default:
                return null;
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fab:
                if (mCurrentEditMode == 0){
                    changeEditMode(1);
                    mCurrentEditMode = 1;
                }else{
                    changeEditMode(0);
                    mCurrentEditMode = 0;
                }

                break;
            case R.id.profile_placeholder:
                ////// TODO: 03.07.2016 сделать выбор, откуда загружать фото;
                showDialog(ConstantManager.LOAD_PROFILE_PHOTO);
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ConstantManager.EDIT_MODE_KEY, mCurrentEditMode);
    }

    private void showSnackbar(String message){
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            mNavigationDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(){

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        mAppBarParams = (AppBarLayout.LayoutParams) mCollapsingToolbar.getLayoutParams();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    /*
     * Переключает режим редактирования
     *  @param mode если 1 - режим редактирования, если 0 - режим просмотра
     */
    private void changeEditMode(int mode){

        if (mode == 1){
            mFab.setImageResource(R.drawable.ic_done_black_24dp);
            for (EditText  userValue: mUserInfoViews) {
                userValue.setEnabled(true);
                userValue.setFocusable(true);
                userValue.setFocusableInTouchMode(true);
                showProfilePlaceholder();
                lockToolbar();
            }
        }else{
            mFab.setImageResource(R.drawable.ic_create_black_24dp);

            for (EditText  userValue: mUserInfoViews) {
                userValue.setEnabled(false);
                userValue.setFocusable(false);
                userValue.setFocusableInTouchMode(false);
                saveUserInfoValue();
                hideProfilePlaceholder();
                unlockToolbar();


            }
        }

    }

    private void loadUserInfoValue(){

        List<String> userData = mDataManager.getPreferencesManager().loadUserProfileData();

        for (int i = 0; i < userData.size(); i++) {
            mUserInfoViews.get(i).setText(userData.get(i));
        }

    }

    private void saveUserInfoValue(){

        List<String> userData = new ArrayList<>();
        for (EditText userFieldView : mUserInfoViews) {
            userData.add(userFieldView.getText().toString());
        }

        mDataManager.getPreferencesManager().saveUserProfileData(userData);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();


        if (mNavigationDrawer.isDrawerOpen(GravityCompat.START)){
            mNavigationDrawer.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();
        }

    }

    private void loadPhotoFromGallery(){

    }

    private void loadPhotoFromCamera(){

    }

    private void hideProfilePlaceholder(){

        mProfilePlaceholder.setVisibility(View.GONE);
    }


    private void showProfilePlaceholder(){

        mProfilePlaceholder.setVisibility(View.VISIBLE);

    }

    private void lockToolbar(){
        mAppBarLayout.setExpanded(true, true);
        mAppBarParams.setScrollFlags(0);
        mCollapsingToolbar.setLayoutParams(mAppBarParams);
    }

    private void unlockToolbar(){

        mAppBarParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        mCollapsingToolbar.setLayoutParams(mAppBarParams);
    }
}
