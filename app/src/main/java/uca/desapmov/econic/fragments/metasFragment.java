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

public class metasFragment extends Fragment {

        private static final String ARG_LIST = "checklist_item";

        private uca.desapmov.econic.adapters.metasAdapter metasAdapter;
        private ArrayList<metasModel> mPointsList;
        private ItemTapListener itemTapListener;

        public metasFragment() {
            // Required empty public constructor
        }

        public static uca.desapmov.econic.fragments.metasFragment newInstance(ArrayList<metasModel> points) {
            uca.desapmov.econic.fragments.metasFragment fragment = new uca.desapmov.econic.fragments.metasFragment();
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
            return inflater.inflate(R.layout.fragment_metas , container, false);
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
            RecyclerView rvMeta = view.findViewById(R.id.rv_metas);
            metasAdapter = new metasAdapter(mPointsList, itemTapListener);
            rvMeta.setAdapter(metasAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rvMeta.setLayoutManager(layoutManager);
            rvMeta.setHasFixedSize(true);
        }

    }
