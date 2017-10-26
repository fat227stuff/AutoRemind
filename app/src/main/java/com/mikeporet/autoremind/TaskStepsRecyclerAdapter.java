package com.mikeporet.autoremind;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mikeporet on 10/25/17.
 */

public class TaskStepsRecyclerAdapter extends RecyclerView.Adapter<TaskStepsRecyclerAdapter.ViewHolder> {

    String[] steps;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView stepTitleView;
        public TextView stepDesc;

        public ViewHolder(final View itemView) {
            super(itemView);
            stepTitleView = (TextView) itemView.findViewById(R.id.checklistTitle);
            stepDesc = (TextView) itemView.findViewById(R.id.checklistDes);
        }

        @Override
        public void onClick(View v) {
            Log.d("testmebabyonemoretime", "poop scooping buggy");


            Intent intent = new Intent(v.getContext(), ModelActivity.class);
            intent.putExtra("Make", steps[getAdapterPosition()]);
            itemView.getContext().startActivity(intent);
        }
    }

    public TaskStepsRecyclerAdapter(String[] supplyTitle) {
        this.steps = supplyTitle;
    }


    @Override
    public TaskStepsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.checklist_card, parent, false);
        TaskStepsRecyclerAdapter.ViewHolder viewHolder = new TaskStepsRecyclerAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskStepsRecyclerAdapter.ViewHolder holder, int position) {
        TextView name = holder.stepTitleView;
        name.setText("Step " + (position + 1));
        TextView desc = holder.stepDesc;
        desc.setText(steps[position]);

    }

    @Override
    public int getItemCount() {
        return steps.length;
    }
}

