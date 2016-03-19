package com.helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Anna on 17/03/2016.
 */
public class SelectedImageActivity extends AppCompatActivity {
    private ArrayList<Integer> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Grumpy Cat Says...");
        setContentView(R.layout.clicked_item);
        /*Get data from the source class.*/
        Intent i = getIntent();
        int imagePosition = i.getIntExtra("imagePosition", -1);
        int image = i.getIntExtra("selectedImage", -1);
        images = i.getIntegerArrayListExtra("images");
        /*End.*/
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        CustomPagerAdapter adapter = new CustomPagerAdapter(this, images, image);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(imagePosition);
    }


    class CustomPagerAdapter extends PagerAdapter {
        Context context;
        LayoutInflater layoutInflater;
        ArrayList<Integer> images;
        int image;


        public CustomPagerAdapter(Context context,  ArrayList<Integer> images, int image) {
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
            this.images = images;
            this.image = image;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = layoutInflater.inflate(R.layout.scrollbar_image, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.scroll_image);
            imageView.setImageResource(images.get(position));
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
