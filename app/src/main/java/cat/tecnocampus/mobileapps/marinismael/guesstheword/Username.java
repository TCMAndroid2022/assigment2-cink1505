package cat.tecnocampus.mobileapps.marinismael.guesstheword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Username extends AppCompatActivity {

    PlayerController playerController;
    EditText playerNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        playerController = new PlayerController(getApplication());
        playerNickname = (EditText) findViewById(R.id.etPlayerNickname);
    }

    public void PlayGame(View view) {
        String nickname = playerNickname.getText().toString();

        if (nickname.matches("")) {
            Toast.makeText(this, "You did not enter a nickname", Toast.LENGTH_SHORT).show();
            return;
        }

        Player player = playerController.getPlayer(nickname);

        if (player == null) {
            player = new Player(nickname,0, 1);
            playerController.insertPlayer(player);
        } else {
            player.setPlays(player.getPlays()+1);
            playerController.updatePlayer(player);
        }

        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("nickname", nickname);
        startActivity(myIntent);
    }
}
