package adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.DetailActivity;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context =context;
        this.movies = movies;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTile;
        TextView tvOverview;
        ImageView tvPoster;
        RelativeLayout container;
        public ViewHolder(View view){
            super(view);
            tvTile = view.findViewById(R.id.tvTitle);
            tvOverview = view.findViewById(R.id.tvOverview);
            tvPoster = view.findViewById(R.id.tvPoster);
            container = view.findViewById(R.id.container);
        }

        public void bind(Movie movie) {
            tvTile.setText(movie.getTitle());
            tvOverview.setText(movie.getOverView());

            String imageUrl;
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackgroundPath();
            }else{
                imageUrl = movie.getPossterPath();
            }
            Glide.with(context).load(imageUrl).into(tvPoster);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
