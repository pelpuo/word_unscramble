package sample;

public class Score {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private String username;
    private int value;

    public Score(String username, int value){
        this.username = username;
        this.value = value;
    }
}
