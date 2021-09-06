package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.MovieAdapter;
import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    private static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static final String TAG = "MainActivity";
    private List<Movie> movies;
    private RecyclerView.Adapter movieAdapter;
    private RecyclerView recyclerViewMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing Recycler view
        movies = new ArrayList<>();
        recyclerViewMovies = findViewById(R.id.rvMovies);
        movieAdapter = new MovieAdapter(this, movies);
        recyclerViewMovies.setAdapter(movieAdapter);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client =new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, " Success");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    Log.d(TAG, "movies" + movies.size());
                }catch (JSONException e){
                    e.printStackTrace();
                    Log.e(TAG, "JsonException ", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, " Failure");
            }
        });

    }
}