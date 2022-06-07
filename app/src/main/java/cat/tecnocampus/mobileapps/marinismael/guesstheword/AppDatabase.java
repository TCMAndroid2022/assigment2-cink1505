package cat.tecnocampus.mobileapps.marinismael.guesstheword;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cat.tecnocampus.mobileapps.marinismael.guesstheword.GameDAO;
import cat.tecnocampus.mobileapps.marinismael.guesstheword.PlayerDAO;
import cat.tecnocampus.mobileapps.marinismael.guesstheword.Game;
import cat.tecnocampus.mobileapps.marinismael.guesstheword.Player;

@Database(entities = {Game.class, Player.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GameDAO gameDAO();
    public abstract PlayerDAO playerDAO();
}
