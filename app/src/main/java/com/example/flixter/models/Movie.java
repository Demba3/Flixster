package com.example.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel

public class Movie {
    String possterPath;
    String title;
    String overView;
    String backgroundPath;
    double rating;
    int movieID;
// For parceler
    public Movie(){

    }
    public Movie(JSONObject jsonObject) throws JSONException {
        possterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overView = jsonObject.getString("overview");
        backgroundPath = jsonObject.getString("backdrop_path");
        rating = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");
    }

    public static List<Movie> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            movies.add(new Movie(jsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPossterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", possterPath);
    }

    public String getBackgroundPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backgroundPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverView() {
        return overView;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieID() {
        return movieID;
    }
}
