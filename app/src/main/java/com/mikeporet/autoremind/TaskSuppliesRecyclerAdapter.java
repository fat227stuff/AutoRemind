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
    private Car car;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView supplyTitleView;
        public TextView supplyDesc;
        public CardView supplyCard;
        private Car car;

        public ViewHolder(final View itemView, Car car) {
            super(itemView);
            supplyTitleView = (TextView) itemView.findViewById(R.id.checklistTitle);
            supplyDesc = (TextView) itemView.findViewById(R.id.checklistDes);
            supplyCard = (CardView) itemView.findViewById(R.id.checklistCard);
            supplyCard.setOnClickListener(this);
            this.car = car;
        }

        @Override
        public void onClick(View v) {
            Log.d("testmebabyonemoretime", "poop scooping buggy");
            String supply = supplyTitleView.getText().toString();
            String search_modifier = "";
            String carMake = car.getMake();
            String carModel = car.getModel();
            String carYear = Integer.toString(car.getYear());

             if ( supply.equals("Engine Oil") | supply.equals("Oil Filter")|supply.equals("Air Filter")|
                     supply.equals("Battery")|supply.equals("Engine Coolant"))
             {
                 search_modifier = carYear + "+" + carMake + "+" + carModel + " +";
             }



            String url = "https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dautomotive&field-keywords=" + search_modifier + supply;

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            itemView.getContext().startActivity(intent);
        }
    }

    public TaskSuppliesRecyclerAdapter(List<Supply> supplyTitle, Car car) {
        this.car = car;
        this.supplyTitle = supplyTitle;
    }


    @Override
    public TaskSuppliesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.checklist_card, parent, false);
        TaskSuppliesRecyclerAdapter.ViewHolder viewHolder = new TaskSuppliesRecyclerAdapter.ViewHolder(contactView, car);
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
