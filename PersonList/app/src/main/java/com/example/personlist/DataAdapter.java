package com.example.personlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    // ivar for DataAdapter
    private Context context;
    private int layoutID;
    private String [] names;

    public DataAdapter(Context context, int layoutID, String[] names) {
        this.context = context;
        this.layoutID = layoutID;
        this.names = names;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout and make it a viewholder
        View v = LayoutInflater.from(this.context).inflate(layoutID, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // populate viewholder with model data
        holder.name.setText(names[position]);
        holder.image.setImageResource(R.drawable.sabin);

        // is there any interaction
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do something with item position
                Toast.makeText(context,
                        ((TextView)view).getText().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }


    // inner class for ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name  = itemView.findViewById(R.id.textView);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
