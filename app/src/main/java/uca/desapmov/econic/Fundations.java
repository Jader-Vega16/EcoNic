package uca.desapmov.econic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import uca.desapmov.econic.helpers.events.ItemTapListener;

import uca.desapmov.econic.fragments.*;
import uca.desapmov.econic.helpers.*;
import uca.desapmov.econic.helpers.events.*;
import uca.desapmov.econic.models.*;
import uca.desapmov.econic.data.*;
import uca.desapmov.econic.models.UserModel;
import uca.desapmov.econic.adapters.*;
import uca.desapmov.econic.adapters.viewholders.*;

public class Fundations extends AppCompatActivity implements ItemTapListener {
    private static final String TAG = Fundations.class.getName();
    private List<fundationModel> mModelList;
    private fundationAssetSource assetSource;
    private fundationModel fundation;
    private ViewGroup rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fundations);
        setup();
    }

    private void setup() {
        assetSource = new fundationAssetSource(getBaseContext());
        mModelList = new ArrayList<>();
        rootView = findViewById(R.id.ly_fundation);
        //setupViewFromData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        if(!mModelList.isEmpty()) {
            Log.d(TAG, "Ya existen valores en la lista");
            return;
        }
        mModelList = assetSource.getAll();
        loadRecycleFragment(new ArrayList<fundationModel>(mModelList));
}

    private void loadRecycleFragment(ArrayList<fundationModel> points) {
        FragmentTransaction frgTran = getSupportFragmentManager().beginTransaction();
        frgTran.replace(R.id.fundations_ph, fundationFragment.newInstance(points));
        frgTran.commit();
    }

    @Override
    public void onItemTap(View view, int position) {
        showMessageWithSelectedItem(position);
    }

    private void showMessageWithSelectedItem(int position) {
        if (mModelList == null) {
            Log.e(TAG, "invalid mModelList");
            return;
        }
        if (position > mModelList.size()) {
            Log.e(TAG, "invalid position");
            return;
        }
    }
}
