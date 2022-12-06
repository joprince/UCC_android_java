package com.example.lab_test_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class dataAdaptor extends RecyclerView.Adapter<dataAdaptor.ViewHolder>{
    private Context context;
    private int rowID;
    private String [] names;
    private String [] currency_rate;

    public dataAdaptor(Context context, int rowID, String[] currency, String[] currency_rate) {
        this.context = context;
        this.rowID = rowID;
        this.names = currency;
        this.currency_rate = currency_rate;
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
        holder.currency.setText(names[position]);
        holder.conversion_rate.setText(currency_rate[position]);

        // still few lines of functionality
        holder.currency.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // do something with names[positions]
//                Intent intent=this.getIntent();
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView currency;
        public TextView conversion_rate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.currency = itemView.findViewById(R.id.textView1);
            this.conversion_rate = itemView.findViewById(R.id.textView2);
        }



    }
}
