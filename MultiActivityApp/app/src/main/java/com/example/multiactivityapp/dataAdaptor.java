package com.example.multiactivityapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dataAdaptor extends RecyclerView.Adapter<dataAdaptor.ViewHolder>{
    private Context context;
    private int rowID;
    private ArrayList<country> countries;

    public dataAdaptor(Context context, int rowID, ArrayList<country> countries) {
        this.context = context;
        this.rowID = rowID;
        this.countries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout and return the tree view
        View v = LayoutInflater.from(this.context).inflate(this.rowID, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // populate bind the view holder fields with data
        holder.countryName.setText(countries.get(position).getName());
        holder.flag.setText(countries.get(position).getEmoji());

        // still few lines of functionality
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do something with names[positions]
                country country = countries.get(holder.getAdapterPosition());
                Log.d("info1", countries.get(holder.getAdapterPosition()).getDescription());
                Intent i = new Intent(view.getContext(), shortDescription.class);
                i.putExtra("country", country);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView flag;
        public TextView countryName;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.flag = itemView.findViewById(R.id.textView1);
            this.countryName = itemView.findViewById(R.id.textView2);
            this.linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
