package com.karljamoralin.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jamorali on 10/26/2016.
 */

public class MovieDBAPI extends AsyncTask<String, Void, List<Movie>> {

    final String TAG = getClass().getSimpleName();

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected List<Movie> doInBackground(String... params) {

        BufferedReader bufferedReader = null;
        HttpsURLConnection httpsURLConnection = null;
        StringBuffer stringBuffer = null;
        try {

            //create a URL
            URL url = new URL(buildURL(params[0]));
            Log.v(TAG, url.toString());
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();

            //get string input
            InputStream inputStream = httpsURLConnection.getInputStream();
            if (inputStream == null) {
                //no input stream, nothing to do
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            stringBuffer = new StringBuffer();
            while((line = bufferedReader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                stringBuffer.append(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            //if stringBuffer is not null, then prepare the result
            if (stringBuffer != null) {
                return getMovieDataFromJSON(stringBuffer.toString());
            }

            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;

        }

        //Parse string input

        //Return M

    }

    private List<Movie> getMovieDataFromJSON(String movieJSONString) {

        final String TMDB_RESULTS = "results";
        final String TMDB_ID = "id";
        final String TMDB_ORIGINAL_TITLE = "original_title";
        final String TMDB_POSTER_PATH = "poster_path";
        final String TMDB_OVERVIEW = "overview";
        final String TMDB_VOTE_AVERAGE = "vote_average";
        final String TMDB_RELEASE_DATE = "release_date";

        JSONObject main = null;
        JSONArray results = null;
        List<Movie> movies = null;
        try {

            main = new JSONObject(movieJSONString);
            results = main.getJSONArray(TMDB_RESULTS);
            movies = new ArrayList<>();

            for (int i=0; i < results.length(); i++) {

                JSONObject movieJSON = results.getJSONObject(i);

                Movie movie = new Movie(movieJSON.getString(TMDB_ID),
                        movieJSON.getString(TMDB_ORIGINAL_TITLE),
                        "http://image.tmdb.org/t/p/w185/" + movieJSON.getString(TMDB_POSTER_PATH),
                        movieJSON.getString(TMDB_OVERVIEW),
                        movieJSON.getString(TMDB_VOTE_AVERAGE),
                        movieJSON.getString(TMDB_RELEASE_DATE));

                movies.add(movie);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return movies;
        }


    }

    String buildURL(String mode) {

        final String SCHEME = "https";
        final String AUTHORITY = "api.themoviedb.org";
        final String PATH1 = "3";
        final String PATH2 = "movie";
        final String API_KEY = "api_key";
        final String LANGUAGE = "language";

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme(SCHEME);
        uriBuilder.authority(AUTHORITY);
        uriBuilder.appendPath(PATH1);
        uriBuilder.appendPath(PATH2);
        uriBuilder.appendPath(mode);
        uriBuilder.appendQueryParameter(API_KEY, "");
        uriBuilder.appendQueryParameter(LANGUAGE, "en-US");

        return uriBuilder.build().toString();

    }

}
