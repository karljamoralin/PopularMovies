package com.karljamoralin.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

/**
 * Created by jamorali on 10/25/2016.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return Movies.ITEMS.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        AwesomeImageView awesomeImageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            awesomeImageView = new AwesomeImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(185, 277));
        } else {
            awesomeImageView = (AwesomeImageView) convertView;
        }

//        imageView.setImageResource(mThumbIds[position]);
        Picasso.with(mContext)
////                .load("http://i.imgur.com/DvpvklR.png")
                .load(Movies.ITEMS.get(position).getPosterPath())
                .fit()
                .into(awesomeImageView);

        return awesomeImageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };



}
