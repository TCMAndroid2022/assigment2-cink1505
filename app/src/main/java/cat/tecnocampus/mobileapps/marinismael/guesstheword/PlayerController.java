package cat.tecnocampus.mobileapps.marinismael.guesstheword;

import android.app.Application;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import cat.tecnocampus.mobileapps.marinismael.guesstheword.GameDAO;
import cat.tecnocampus.mobileapps.marinismael.guesstheword.PlayerDAO;
import cat.tecnocampus.mobileapps.marinismael.guesstheword.Game;
import cat.tecnocampus.mobileapps.marinismael.guesstheword.Player;

public class PlayerController {
    private static PlayerController playerController;
    private PlayerDAO playerDAO;
    private GameDAO gameDAO;

    public PlayerController(Application application) {
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class,
                "database-name").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        playerDAO = db.playerDAO();
        gameDAO = db.gameDAO();
    }

    public void insertPlayer(Player player) { playerDAO.insertPlayer(player); }
    public void insertGame(Game game) { gameDAO.insertGame(game); }

    public void updatePlayer(Player player) { playerDAO.updatePlayer(player); }

    public List<Player> listPlayers() { return playerDAO.getPlayers(); }
    public Player getPlayer(String nickname) { return playerDAO.getPlayerbyName(nickname); }
    public List<Game> listGames() { return gameDAO.getGames(); }
    public List<Game> listPlayerGames(String nickname) { return gameDAO.getPlayerGames(nickname); }
}
