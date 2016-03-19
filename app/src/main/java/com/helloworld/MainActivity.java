package com.helloworld;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Integer> images;
    public ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Grumpy Cat");
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView)findViewById(R.id.grid_view);
        images = new ArrayList<>();
        images.add(R.drawable.cat1);
        images.add(R.drawable.cat2);
        images.add(R.drawable.cat3);
        images.add(R.drawable.cat4);
        images.add(R.drawable.cat5);
        images.add(R.drawable.cat6);
        imageAdapter = new ImageAdapter(this, images);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer image = (Integer)imageAdapter.getItem(position);
                Intent i = new Intent(getApplicationContext(), SelectedImageActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("imagePosition", position);
                extras.putInt("selectedImage", image);
                extras.putIntegerArrayList("images", images);
                i.putExtras(extras);
                startActivity(i);
            }
        });
    }

    public class ImageAdapter extends ArrayAdapter {

        public ImageAdapter(Context context, ArrayList<Integer> resource) {
            super(context, R.layout.image_view, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            Integer image = (Integer)getItem(position);
            if(convertView == null){
                convertView =  LayoutInflater.from(getApplicationContext()).inflate(R.layout.image_view, parent, false);
            }
            ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
            imageView.setImageResource(image);
            return convertView;
        }
    }
}





