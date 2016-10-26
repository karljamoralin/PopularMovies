package com.karljamoralin.popularmovies;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jamorali on 10/26/2016.
 */
public class Movies {
    private static Movies ourInstance = new Movies();

    public static Movies getInstance() {
        return ourInstance;
    }

    private Movies() {
    }

    public static List<Movie> ITEMS = new ArrayList<>();

    public static void get(String mode) {

        MovieDBAPI movieDBAPI = new MovieDBAPI();
        movieDBAPI.execute(mode);
        try {
            Movies.ITEMS.clear();
            Movies.ITEMS.addAll(movieDBAPI.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
