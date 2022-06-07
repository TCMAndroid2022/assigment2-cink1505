package cat.tecnocampus.mobileapps.marinismael.guesstheword;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"nickname"}, unique = true)})
public class Player {
    @PrimaryKey (autoGenerate = true)
    public int id;
    public String nickname;
    public int score;
    public int plays;

    public Player(String nickname, int score, int plays) {
        this.nickname = nickname;
        this.score = score;
        this.plays = plays;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) { this.score = score; }

    public int getPlays() { return plays; }

    public void setPlays(int plays) { this.plays = plays; }
}
