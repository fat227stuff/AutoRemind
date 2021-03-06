package com.mikeporet.autoremind;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mikeporet on 10/18/17.
 */

public class CarMakesRecyclerAdapter extends RecyclerView.Adapter<CarMakesRecyclerAdapter.ViewHolder> {
    private final HashMap<String, ArrayList<String>> makesModels;
    ArrayList<String> makeList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView makeName;


        public ViewHolder(final View itemView) {
            super(itemView);
            makeName = (TextView) itemView.findViewById(R.id.car_list_item);
            makeName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("testmebabyonemoretime", "poop scooping buggy");


            Intent intent = new Intent(v.getContext(), ModelActivity.class);
            String makeName = makeList.get(getAdapterPosition());
            intent.putExtra("Make", makeName);
            ArrayList<String> models = makesModels.get(makeName);
            intent.putExtra("Models", models);
            itemView.getContext().startActivity(intent);
        }
    }

    public CarMakesRecyclerAdapter(ArrayList<String> makeList, HashMap<String, ArrayList<String>> makesModels) {
        this.makeList = makeList;
        this.makesModels = makesModels;
    }


    @Override
    public CarMakesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.car_menu_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CarMakesRecyclerAdapter.ViewHolder holder, int position) {
        TextView name = holder.makeName;
        name.setText(makeList.get(position));

    }

    @Override
    public int getItemCount() {
        return makeList.size();
    }
}
