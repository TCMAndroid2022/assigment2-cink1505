package cat.tecnocampus.mobileapps.marinismael.mobileapp.domain;

public class User {
    private int points;
    private String name;

    public User(String name){
        this.name=name;
        points=0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int puntos) {
        this.points = puntos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
