package cat.tecnocampus.mobileapps.marinismael.guesstheword;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cat.tecnocampus.mobileapps.marinismael.guesstheword.R;
import cat.tecnocampus.mobileapps.marinismael.guesstheword.Game;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private ArrayList<Game> games = new ArrayList<Game>();

    public DetailAdapter(ArrayList<Game> games) {
        this.games = games;
    }

    // Primary methods of the adapter
    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        try {
            Game game = games.get(position);

            TextView tvNickname = holder.tvWord;
            tvNickname.setText(game.getWord());

            TextView tvScore = holder.tvScore;
            int score = game.getScore();
            tvScore.setText(""+score);
        } catch (NullPointerException e) {
            Log.e("Adapter", "onBindViewHolder: Null Pointer: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    // The ViewHolder describes and provides access to all the views within each item row
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvWord, tvScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.game_word);
            tvScore = (TextView) itemView.findViewById(R.id.game_score);
        }
    }
}