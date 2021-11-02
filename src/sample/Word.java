package sample;

public class Word {
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private String value;
    private int level;

    public Word(String value, int level){
        this.value = value;
        this.level = level;
    }



}
