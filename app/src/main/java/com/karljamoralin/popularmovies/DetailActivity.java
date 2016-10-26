package com.karljamoralin.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int pos = getIntent().getIntExtra("pos", -1);

        Movie movie = Movies.ITEMS.get(pos);

        TextView title = (TextView) findViewById(R.id.title);
        ImageView poster = (ImageView) findViewById(R.id.poster);
        TextView overview = (TextView) findViewById(R.id.overview);

        title.setText(movie.getOriginalTitle());
        Picasso.with(this)
                .load(movie.getPosterPath())
                .fit()
                .into(poster);
        overview.setText(movie.getOverview());

    }
}
