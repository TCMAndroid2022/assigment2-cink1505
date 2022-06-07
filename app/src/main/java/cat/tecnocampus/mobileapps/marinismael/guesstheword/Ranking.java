package cat.tecnocampus.mobileapps.marinismael.guesstheword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity implements RankingAdapter.OnPlayerListener {

    private RecyclerView recyclerView;
    PlayerController playerController;

    // Variables
    private List<Player> players = new ArrayList<Player>();
    private RankingAdapter rankingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        recyclerView = (RecyclerView) findViewById(R.id.rvPlayers);
        playerController = new PlayerController(getApplication());

        initRecyclerView();
        showPlayers();
    }

    private void initRecyclerView(){
        rankingAdapter = new RankingAdapter((ArrayList<Player>) players, this);
        recyclerView.setAdapter(rankingAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void showPlayers(){
        List<Player> currentPlayers = playerController.listPlayers();
        for(Player p: currentPlayers)
            players.add(p);
        rankingAdapter.notifyDataSetChanged();
    }

    public void PlayAgain(View view) {
        Intent myIntent = new Intent(this, Username.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(myIntent);
    }

    @Override
    public void OnPlayerClick(int position) {
        Player player = players.get(position);
        Intent myIntent = new Intent(this, PlayerInfo.class);
        myIntent.putExtra("nickname", player.getNickname());
        startActivity(myIntent);
    }
}
