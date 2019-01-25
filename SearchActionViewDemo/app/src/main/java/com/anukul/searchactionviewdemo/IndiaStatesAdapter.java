package com.anukul.searchactionviewdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class IndiaStatesAdapter extends RecyclerView.Adapter<IndiaStatesAdapter.IndiaStatesViewHolder>{

    private ArrayList<String> statesOfIndiaArrayList;

    public IndiaStatesAdapter(ArrayList<String> statesOfIndiaArrayList) {
        this.statesOfIndiaArrayList = statesOfIndiaArrayList;
    }

    public class IndiaStatesViewHolder extends RecyclerView.ViewHolder {
        TextView indiaStatesTv;
        public IndiaStatesViewHolder(@NonNull View itemView) {
            super(itemView);
            indiaStatesTv = itemView.findViewById(R.id.india_statesTv);

        }
    }
    @NonNull
    @Override
    public IndiaStatesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.india_states,viewGroup,false);

        return new IndiaStatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndiaStatesViewHolder indiaStatesViewHolder, int i) {
        String indiaStates = statesOfIndiaArrayList.get(i);
        indiaStatesViewHolder.indiaStatesTv.setText(indiaStates);
    }

    @Override
    public int getItemCount() {
        return statesOfIndiaArrayList.size();
    }

    public void updateList(ArrayList<String> searchIndiaStatesArrayList){
        statesOfIndiaArrayList = new ArrayList<>();
        statesOfIndiaArrayList.addAll(searchIndiaStatesArrayList);
        notifyDataSetChanged();
    }

}
