package uca.desapmov.econic.adapters.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import uca.desapmov.econic.R;
import uca.desapmov.econic.helpers.events.ItemTapListener;

public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView recycle_title, recycle_desc;
    public Button recycle_edit;
    public ImageView recycle_img;

    @Nullable
    private final ItemTapListener mTapListener;

    public RecycleViewHolder(@NonNull View itemView, @Nullable ItemTapListener tapListener) {
            super(itemView);

            mTapListener = tapListener;
            itemView.setOnClickListener(this);

            recycle_title = itemView.findViewById(R.id.recycle_title);
            recycle_desc = itemView.findViewById(R.id.recycle_desc);
            recycle_edit = itemView.findViewById(R.id.recycle_edit);
            recycle_img  = itemView.findViewById(R.id.recycle_image);
        }

    @Override
    public void onClick(View view) {
            if(mTapListener == null) return;
            mTapListener.onItemTap(view, getAdapterPosition());
            }

}

