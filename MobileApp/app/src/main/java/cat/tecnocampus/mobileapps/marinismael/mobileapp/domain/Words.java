package cat.tecnocampus.mobileapps.marinismael.mobileapp.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Words {

    public Letter[] letters;

    public Words() {
        String[] word = getRandomWord().split("\\a");
        letters = new Letter[word.length];

        for (int i = 0; i < word.length; i++) {
            letters[i] = new Letter(word[i]);
        }

    }

    public String getRandomWord() {
        String randomWord = "";
        try {
            URL url = new URL("https://random-word-api.herokuapp.com/word");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                randomWord = line;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return randomWord;
    }

    public Letter[] getWord() {
        return letters;
    }

}
