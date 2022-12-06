package com.example.multiaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity {

    private TextView nameTV = null;
    private ImageView personIV = null;
    private Button moreInfoBT = null;


    //data
    private Person data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        // wire objects with widgets
        nameTV = findViewById(R.id.textView);
        personIV = findViewById(R.id.imageView);
        moreInfoBT = findViewById(R.id.button);

        // data
        data = new Person(
                "Jewel",
                "WGB",
                "12344",
                "jewel.jpg",
                "htttp:www.ucc.ie/"
        );

        //populate widgets with data
        nameTV.setText(data.getName());
        //personIV.setImageResource(R.drawable.);
        String imageName = data.getImage();
        imageName = imageName.substring(0, imageName.indexOf("."));
        int imageID = this.getResources().getIdentifier(imageName, "drawable", getPackageName());
        personIV.setImageResource((imageID));

        // on click

        moreInfoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create and load intent
                Intent intent = new Intent(
                        PersonActivity.this,
                        DetailsActivity.class
                        );
                Bundle bundle = new Bundle();
                bundle.putSerializable(
                        "data",
                        data
                );
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}