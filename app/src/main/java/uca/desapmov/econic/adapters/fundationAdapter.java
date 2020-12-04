package uca.desapmov.econic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.*;


import java.util.List;

import uca.desapmov.econic.adapters.viewholders.RecycleViewHolder;
import  uca.desapmov.econic.R;
import uca.desapmov.econic.helpers.events.ItemTapListener;
import uca.desapmov.econic.models.fundationModel;

public class fundationAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    @NonNull
    private List<fundationModel> mModelList;
    @Nullable
    private final ItemTapListener mTapListener;


    public fundationAdapter(@NonNull List<fundationModel> modelList, @Nullable ItemTapListener tapListener) {
        mModelList = modelList;
        mTapListener = tapListener;
    }

    public void updateList(List<fundationModel> newList) {
        mModelList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        RecycleViewHolder viewHolder = new RecycleViewHolder(itemView, mTapListener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        fundationModel currentModel = mModelList.get(position);
        holder.recycle_title.setText(currentModel.getNombre());
        holder.recycle_desc.setText(currentModel.getDescripcion());
        Picasso.get().load(currentModel.getImgUrl()).into(holder.recycle_img);
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }


}
