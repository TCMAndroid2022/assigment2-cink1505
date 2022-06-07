package cat.tecnocampus.mobileapps.marinismael.guesstheword;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private ArrayList<Player> players = new ArrayList<Player>();
    private OnPlayerListener onPlayerListener;

    public RankingAdapter(ArrayList<Player> players, OnPlayerListener onPlayerListener) {
        this.players = players;
        this.onPlayerListener = onPlayerListener;
    }

    // Primary methods of the adapter
    @NonNull
    @Override
    public RankingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new ViewHolder(view, onPlayerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingAdapter.ViewHolder holder, int position) {
        try {
            Player player = players.get(position);

            TextView tvNickname = holder.tvNickname;
            tvNickname.setText(player.getNickname());

            TextView tvScore = holder.tvScore;
            int score = player.getScore();
            tvScore.setText(""+score);

            TextView tvPlays = holder.tvPlays;
            int plays = player.getPlays();
            tvPlays.setText(""+plays);

        } catch (NullPointerException e) {
            Log.e("Adapter", "onBindViewHolder: Null Pointer: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    // The ViewHolder describes and provides access to all the views within each item row

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvNickname, tvScore, tvPlays;
        OnPlayerListener onPlayerListener;

        public ViewHolder(@NonNull View itemView, OnPlayerListener onPlayerListener) {
            super(itemView);

            tvNickname = (TextView) itemView.findViewById(R.id.player_nickname);
            tvScore = (TextView) itemView.findViewById(R.id.player_score);
            tvPlays = (TextView) itemView.findViewById(R.id.player_plays);
            this.onPlayerListener = onPlayerListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onPlayerListener.OnPlayerClick(getBindingAdapterPosition());
        }
    }

    public interface OnPlayerListener {
        void OnPlayerClick(int position);
    }

}
