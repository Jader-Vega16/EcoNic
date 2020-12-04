package uca.desapmov.econic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import uca.desapmov.econic.fragments.*;
import uca.desapmov.econic.helpers.*;
import uca.desapmov.econic.helpers.events.*;
import uca.desapmov.econic.models.*;
import uca.desapmov.econic.data.*;
import uca.desapmov.econic.models.UserModel;
import uca.desapmov.econic.adapters.*;
import uca.desapmov.econic.adapters.viewholders.*;

public class MainActivity extends AppCompatActivity implements ItemTapListener{

    private static final String TAG = MainActivity.class.getName();
    public static final String FULLNAME_KEY = "NOMBRE";
    public static final String EMAIL_KEY = "EMAIL";
    public static final String PWD_KEY = "PASSWORD";

    private List<RecycleModel> mModelList;
    private RecycleAssetSource assetSource;

    private ViewGroup rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void setup() {
        assetSource = new RecycleAssetSource(getBaseContext());
        mModelList = new ArrayList<>();
        rootView = findViewById(R.id.ly_root);
        setupViewFromData();
        Button btnFundation = findViewById(R.id.buton);

        btnFundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFundations();
            }
        });
    }

    private void setupViewFromData() {
        Intent startIntent = getIntent();
        if(startIntent == null) {
            Toast.makeText(
                    this,
                    "Algo salió mal al obtener los datos :(",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        UserModel userModel = getUserModelFromSources(startIntent.getExtras());

        /**if(getSupportActionBar() != null) {
            String fullname = TextUtils.isEmpty(userModel.getFullname()) ?
                    "Usuario" : userModel.getFullname();
            getSupportActionBar()
                    .setTitle(getString(R.string.welcome_user_title, fullname));
        }

        if(TextUtils.isEmpty(userModel.getEmail())) {
            Toast.makeText(
                    this,
                    R.string.cannot_get_email,
                    Toast.LENGTH_SHORT
            ).show();
        }**/
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

    private void loadData() {
        if(!mModelList.isEmpty()) {
            Log.d(TAG, "Ya existen valores en la lista");
            return;
        }
        mModelList = assetSource.getAll();
        loadRecycleFragment(new ArrayList<RecycleModel>(mModelList));
    }

    private void loadRecycleFragment(ArrayList<RecycleModel> points) {
        FragmentTransaction frgTran = getSupportFragmentManager().beginTransaction();
        frgTran.replace(R.id.points_ph, RecycleFragment.newInstance(points));
        frgTran.commit();
    }

    @Override
    public void onItemTap(View view, int position) {
        showMessageWithSelectedItem(position);
    }

    private void showMessageWithSelectedItem(int position) {
        if(mModelList == null) {
            Log.e(TAG, "invalid mModelList");
            return;
        }
        if(position > mModelList.size()) {
            Log.e(TAG, "invalid position");
            return;
        }
/**
        RecycleModel selectedItemModel = mModelList.get(position);
        navigateToProfile(selectedItemModel);
 **/
    }
/**
    private void navigateToProfile(RecycleModel point) {
        if(isTwoPaneLayout()) {
            loadDetailsFragment(point);
        } else {
            launchProfileActivity(point);
        }
    }

    private void launchProfileActivity(RecycleModel point) {
        Intent intent = new Intent(this, PointProfileActivity.class);
        intent.putExtra(PointProfileActivity.ARG_POINT, point);
        startActivity(intent);
    }

    private void loadDetailsFragment(RecycleModel point) {
        FragmentTransaction frgTran = getSupportFragmentManager().beginTransaction();
        frgTran.replace(R.id.point_profile_ph, PointProfileFragment.newInstance(point));
        frgTran.commit();
    }

    private void showMessageWithPoint(RecycleModel selectedItemModel) {
        Snackbar.make(rootView,
                String.format(Locale.getDefault(),
                        "Has seleccionado %s", selectedItemModel.getName()
                ),
                Snackbar.LENGTH_LONG
        ).show();
    }

    private boolean isTwoPaneLayout() {
        return  findViewById(R.id.point_profile_ph) != null;
    }
    **/

    private void navigateToFundations() {
        Intent intent = new Intent(this, Fundations.class);
        startActivity(intent);
    }
}