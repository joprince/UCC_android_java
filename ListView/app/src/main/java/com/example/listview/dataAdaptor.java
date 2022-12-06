package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class dataAdaptor extends RecyclerView.Adapter<dataAdaptor.ViewHolder>{

    private Context context;
    private int rowID;
    private String [] names;

    public dataAdaptor(Context context, int rowID, String[] names) {
        this.context = context;
        this.rowID = rowID;
        this.names = names;
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
        // populate / bind the view holder fields with data
        holder.name.setText(names[position]);
        holder.icon.setImageResource(R.drawable.img);

        // still few lines of functionality
        holder.name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // do something with names[positions]
                Toast.makeText(context, holder.name.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.textView);
            this.icon = itemView.findViewById(R.id.imageView);
        }



    }
}
