package cat.tecnocampus.mobileapps.marinismael.guesstheword;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Game {
    @PrimaryKey (autoGenerate = true)
    public int id;
    public String word;
    public int score;
    public String nickname;

    public Game(String word, int score, String nickname) {
        this.word = word;
        this.score = score;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public int getScore() {
        return score;
    }

    public String getNickname() {
        return nickname;
    }
}
