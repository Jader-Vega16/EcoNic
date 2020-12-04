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
import uca.desapmov.econic.models.metasModel;

public class metasAdapter  extends RecyclerView.Adapter<RecycleViewHolder> {

    @NonNull
    private List<metasModel> mModelList;
    @Nullable
    private final ItemTapListener mTapListener;


    public metasAdapter(@NonNull List<metasModel> modelList, @Nullable ItemTapListener tapListener) {
        mModelList = modelList;
        mTapListener = tapListener;
    }

    public void updateList(List<metasModel> newList) {
        mModelList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.checklist_item, parent, false);
        RecycleViewHolder viewHolder = new RecycleViewHolder(itemView, mTapListener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        metasModel currentModel = mModelList.get(position);
        holder.recycle_title.setText(currentModel.getNombre());
        holder.recycle_desc.setText(currentModel.getmeta());
        Picasso.get().load(currentModel.getImgUrl()).into(holder.recycle_img);
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }


}
