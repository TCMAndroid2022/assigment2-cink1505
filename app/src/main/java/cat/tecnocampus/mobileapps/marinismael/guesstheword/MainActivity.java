package cat.tecnocampus.mobileapps.marinismael.guesstheword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    private List<String> wordLetters = new ArrayList<>();
    PlayerController playerController;
    TextView tvWord;
    TextView tvPunctuation;
    EditText etWord;
    EditText etSolve;
    Button btEnter;
    Button btSolve;
    private String username;
    private String word = "";
    private String[] letters;
    private int tries;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWord();
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            username = extras.getString("nickname");
        }
        playerController = new PlayerController(getApplication());
        tvWord = findViewById(R.id.tvWord);
        tvPunctuation = findViewById(R.id.tvPunctuation);
        etWord = findViewById(R.id.etWord);
        etSolve= findViewById(R.id.etSolve);
        btEnter = findViewById(R.id.btEnter);
        btSolve = findViewById(R.id.btSolve);
    }
    private void startGame() {
        createList();
        createGame();
    }
    private void getWord() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://palabras-aleatorias-public-api.herokuapp.com/random";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject body = response.getJSONObject("body");
                        word = body.getString("Word").toUpperCase(Locale.ROOT);
                        startGame();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> word = "ERROR");
        queue.add(jsonObjectRequest);
    }
    private void createList() {
        int length = word.length();
        for (int i = 0; i < length; i++) {
            wordLetters.add(word.substring(i, Math.min(length, i+1)));
        }
    }
    private void createGame(){
        tvWord.setText("");
        letters = new String[word.length()];
        for(int i = 0; i < letters.length; i++){
            letters[i] = "_ ";
            tvWord.append(letters[i]);
        }
    }
    private void changeWord(String letter){
        tvWord.setText("");
        int[] indexes = IntStream.range(0, wordLetters.size()).filter(i -> wordLetters.get(i).equals(letter.toUpperCase())).toArray();
        for(int i = 0; i < indexes.length; i++){
            letters[indexes[i]] = letter.toUpperCase();
        }
        for(int i = 0; i < letters.length; i++){
            tvWord.append(letters[i]);
        }
    }
    public void onClickEnter(View view){
        String letter = etWord.getText().toString();
        if(wordLetters.contains(letter.toUpperCase())){
            changeWord(letter);
        }
        etWord.setText("");
        tries++;
    }
    public void onClickSolve(View view){
        getPoints();
        tvPunctuation.append("" + points);
        tvWord.setText("The word is " + word);
        etWord.setVisibility(View.INVISIBLE);
        btEnter.setVisibility(View.INVISIBLE);
        etSolve.setVisibility(View.INVISIBLE);
        btSolve.setVisibility(View.INVISIBLE);
    }
    private void getPoints(){
        if(word.toUpperCase().equals(etSolve.getText().toString().toUpperCase())){
            float letterSolving = wordLetters.size() - tries;
            float wordSolving = letterSolving/wordLetters.size();
            float solving = wordSolving*10;
            points = (int) solving;
        } else {
            points = 0;
        }
    }
    public void getRanking(View view) {
        updateDatabase();
        Intent myIntent = new Intent(this, Ranking.class);
        startActivity(myIntent);
    }
    public void updateDatabase() {
        Game game = new Game(word, points, username);
        playerController.insertGame(game);
        int score = 0;
        List<Game> playerGames = playerController.listPlayerGames(username);
        for(Game g: playerGames) { score += g.getScore(); }
        Player player = playerController.getPlayer(username);
        player.setScore(score);
        playerController.updatePlayer(player);
    }
}