package com.example.mavin.certamen2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mavin.certamen2.model.github;
import com.example.mavin.certamen2.views.AdapterUI;

import java.util.List;

/**
 * Created by mavin on 30/9/2016.
 */

public class UIAdapter extends RecyclerView.Adapter<UIAdapter.ViewHolder> implements AdapterUI {
    private List<github> mDataset;




    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mName;
        public TextView mDescription;
        public TextView mUpdated;


        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            mName = (TextView) v.findViewById(R.id.textName);
            mDescription= (TextView) v.findViewById(R.id.textDescription);
            mUpdated= (TextView) v.findViewById(R.id.textUpdated);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public UIAdapter(List<github> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public UIAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        github git = mDataset.get(position);
        holder.mName.setText(git.getName());
        holder.mDescription.setText(git.getDescription());
        holder.mUpdated.setText(git.getUpdated_at());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
