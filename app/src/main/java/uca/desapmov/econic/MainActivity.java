package uca.desapmov.econic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import uca.desapmov.econic.data.RecycleAssetSource;
import uca.desapmov.econic.fragments.HomeFragment;
import uca.desapmov.econic.fragments.SettingsFragment;
import uca.desapmov.econic.fragments.RecycleFragment;
import uca.desapmov.econic.models.RecycleModel;
import uca.desapmov.econic.fragments.*;
import uca.desapmov.econic.helpers.events.*;
import uca.desapmov.econic.models.*;
import uca.desapmov.econic.data.*;

public class MainActivity extends AppCompatActivity implements ItemTapListener{

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    // urls to load navigation header background image
    // and profile image
    //private static final String urlNavHeaderBg = "https://api.androidhive.info/images/nav-menu-header-bg.jpg";
    //private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG = MainActivity.class.getName();
    private static final String TAG_HOME = "Home";
    private static final String TAG_PHOTOS = "Areas Protegidas";
    private static final String TAG_MOVIES = "Fundaciones";
    private static final String TAG_NOTIFICATIONS = "Retos";
    private static final String TAG_SETTINGS = "Ideas Sostenibles";
    public static final String FULLNAME_KEY = "NOMBRE";
    public static final String EMAIL_KEY = "EMAIL";
    public static final String PWD_KEY = "PASSWORD";
    public static String CURRENT_TAG = TAG_HOME;

    //FUNDATIONS
    private List<fundationModel> mModelListFund;
    private fundationAssetSource assetSourceFund;
    private fundationModel fundation;

    //AREAS PROTEGIDAS
    private List<RecycleModel> mModelListArea;
    private RecycleAssetSource assetSourceArea;

    //Metas
    private List<metasModel> mModelListMeta;
    private metasAssetSource assetSourceMeta;

    private ViewGroup rootView;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    private RecycleFragment recycleFrag;
    private fundationFragment fundationFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        //// Navigation view header
        //navHeader = navigationView.getHeaderView(0);
        //txtName = (TextView) navHeader.findViewById(R.id.name);
        //txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        //imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        //imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        //// load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

        setup();
    }

    private void setup() {
        setupViewFromData();  //CHANGE
        rootView = findViewById(R.id.ly_root);

    }

    private void setupViewFromData() {
        Intent startIntent = getIntent();
        if (startIntent == null) {
            Toast.makeText(
                    this,
                    "Algo salió mal al obtener los datos :(",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        UserModel userModel = getUserModelFromSources(startIntent.getExtras());
        String fullname = TextUtils.isEmpty(userModel.getFullname()) ?
                "Usuario" : userModel.getFullname();

        Toast.makeText(
            this,
                getString(R.string.welcome_user_title, fullname),
            Toast.LENGTH_SHORT
        ).show();

        if(TextUtils.isEmpty(userModel.getEmail())) {
            Toast.makeText(
                    this,
                    R.string.cannot_get_email,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

     @NonNull
     private UserModel getUserModelFromSources(Bundle extras) {
        UserConfig userConfig = new UserConfig(getApplicationContext());
        final UserModel user = userConfig.getUser();
        if(user != null) {
            return user;
        }
         if(extras == null) {
             throw new InvalidParameterException("Extras");
        }
            return new UserModel(extras.getString(FULLNAME_KEY), extras.getString(EMAIL_KEY), extras.getString(PWD_KEY));
         }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */



    @Override
    public void onItemTap(View view, int position) {
        showMessageWithSelectedItem(position);
    }

    private void showMessageWithSelectedItem(int position) {
        if (mModelListArea == null) {
            Log.e(TAG, "invalid mModelList");
            return;
        }
        if (position > mModelListArea.size()) {
            Log.e(TAG, "invalid position");
            return;
        }
    }

    private void loadNavHeader() {
        //    // name, website
        //   txtName.setText("Ravi Tamada");
        //    txtWebsite.setText("www.androidhive.info");
//
        //      // loading header background image
        //    Glide.with(this).load(urlNavHeaderBg)
        //          .diskCacheStrategy(DiskCacheStrategy.ALL)
        //        .into(imgNavHeaderBg);

        // Loading profile image
        //Glide.with(this).load(urlProfileImg)
        //      .thumbnail(0.5f)
        //    .diskCacheStrategy(DiskCacheStrategy.ALL)
        //  .into(imgProfile);

        // showing dot next to notifications label
        //navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment(){
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                // areas protegidas
                assetSourceArea = new RecycleAssetSource(getBaseContext());
                mModelListArea = new ArrayList<>();

                if(!mModelListArea.isEmpty()) {
                    Log.d(TAG, "Ya existen valores en la lista");
                    break;
                }
                mModelListArea = assetSourceArea.getAll();
                ArrayList<RecycleModel> points1 = new ArrayList<RecycleModel>(mModelListArea);

                return RecycleFragment.newInstance(points1);
            case 2:
                // movies fragment
                assetSourceFund = new fundationAssetSource(getBaseContext());
                mModelListFund = new ArrayList<>();

                if(!mModelListFund.isEmpty()) {
                    Log.d(TAG, "Ya existen valores en la lista");
                    break;
                }
                mModelListFund = assetSourceFund.getAll();
                ArrayList<fundationModel> points2 = new ArrayList<fundationModel>(mModelListFund);

                return fundationFragment.newInstance(points2);

            case 3:
                // notifications fragment
                assetSourceMeta = new metasAssetSource(getBaseContext());
                mModelListMeta = new ArrayList<>();

                if(!mModelListFund.isEmpty()) {
                    Log.d(TAG, "Ya existen valores en la lista");
                    break;
                }
                mModelListMeta = assetSourceMeta.getAll();
                ArrayList<metasModel> points3 = new ArrayList<metasModel>(mModelListMeta);
                return metasFragment.newInstance(points3);

            case 4:
                // settings fragment
                assetSourceMeta = new metasAssetSource(getBaseContext());
                mModelListMeta = new ArrayList<>();

                if(!mModelListFund.isEmpty()) {
                    Log.d(TAG, "Ya existen valores en la lista");
                    break;
                }
                mModelListMeta = assetSourceMeta.getAll();
                ArrayList<metasModel> points4 = new ArrayList<metasModel>(mModelListMeta);
                return metasFragment.newInstance(points4);
            default:
                return new HomeFragment();
        }
        return new HomeFragment();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_photos:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.nav_movies:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    /**case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        //startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        //drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        //startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        //drawer.closeDrawers();
                        return true;**/
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }

        // user is in notifications fragment
        // and selected 'Mark all as Read'
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        // user is in notifications fragment
        // and selected 'Clear All'
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }
}