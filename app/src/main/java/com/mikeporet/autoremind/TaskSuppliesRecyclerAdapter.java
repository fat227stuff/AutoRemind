package com.mikeporet.autoremind;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikeporet on 10/25/17.
 */

public class TaskSuppliesRecyclerAdapter extends RecyclerView.Adapter<TaskSuppliesRecyclerAdapter.ViewHolder> {
    List<Supply> supplyTitle;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView supplyTitleView;
        public TextView supplyDesc;
        public ImageView supplyImage;
        public CardView supplyCard;

        public ViewHolder(final View itemView) {
            super(itemView);
            supplyTitleView = (TextView) itemView.findViewById(R.id.checklistTitle);
            supplyDesc = (TextView) itemView.findViewById(R.id.checklistDes);
            supplyImage = (ImageView) itemView.findViewById(R.id.checklistIcon);
            supplyCard = (CardView) itemView.findViewById(R.id.checklistCard);
            supplyCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("testmebabyonemoretime", "poop scooping buggy");

            String carMake = "Honda";
            String carModel = "Civic";
            String carYear = "2005";
            String supply = supplyTitleView.getText().toString();
            String url = "https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dautomotive&field-keywords=" + carYear + "+" + carMake + "+" + carModel + " +" + supply;

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            itemView.getContext().startActivity(intent);
        }
    }

    public TaskSuppliesRecyclerAdapter(List<Supply> supplyTitle) {
        this.supplyTitle = supplyTitle;
    }


    @Override
    public TaskSuppliesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.checklist_card, parent, false);
        TaskSuppliesRecyclerAdapter.ViewHolder viewHolder = new TaskSuppliesRecyclerAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskSuppliesRecyclerAdapter.ViewHolder holder, int position) {
        TextView name = holder.supplyTitleView;
        name.setText(supplyTitle.get(position).getTitle());
        TextView subTitle = holder.supplyDesc;
        subTitle.setText(supplyTitle.get(position).getSubTitle());
    }

    @Override
    public int getItemCount() {
        return supplyTitle.size();
    }
}
