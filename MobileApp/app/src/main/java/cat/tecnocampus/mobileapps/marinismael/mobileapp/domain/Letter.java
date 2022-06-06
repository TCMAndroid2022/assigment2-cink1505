package cat.tecnocampus.mobileapps.marinismael.mobileapp.domain;


import android.os.Parcel;
import android.os.Parcelable;

public class Letter {
    private String type;
    private String letter;

    public String getType() {
        return type;
    }
    public String getLetter() {
        return letter;
    }
    public void setType(String type) { this.type = type; }


    public Letter(String letterWord){
        this.type = "Ocult";
        this.letter = letterWord;

    }
}
