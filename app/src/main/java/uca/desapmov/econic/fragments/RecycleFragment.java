package uca.desapmov.econic.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uca.desapmov.econic.R;
import uca.desapmov.econic.adapters.*;
import uca.desapmov.econic.models.*;
import uca.desapmov.econic.helpers.*;
import uca.desapmov.econic.helpers.events.ItemTapListener;

import java.util.ArrayList;

public class RecycleFragment extends Fragment {

    private static final String ARG_LIST = "list_item";

    private RecycleAdapter recycleAdapter;
    private ArrayList<RecycleModel> mPointsList;
    private ItemTapListener itemTapListener;

    public RecycleFragment() {
        // Required empty public constructor
    }

    public static RecycleFragment newInstance(ArrayList<RecycleModel> points) {
        RecycleFragment fragment = new RecycleFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST, points);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPointsList = getArguments().getParcelableArrayList(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_areas_protegidas , container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setup(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(!(context instanceof ItemTapListener)) {
            throw new ClassCastException("Actividad padre no implementa ItemTapListener");
        }

        itemTapListener = (ItemTapListener)context;
    }

    private void setup(@NonNull View view) {
        setupPointListView(view);
    }

    private void setupPointListView(View view) {
        RecyclerView rvAreas = view.findViewById(R.id.rv_areas);
        recycleAdapter = new RecycleAdapter(mPointsList, itemTapListener);
        rvAreas.setAdapter(recycleAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvAreas.setLayoutManager(layoutManager);
        rvAreas.setHasFixedSize(true);
    }

}
