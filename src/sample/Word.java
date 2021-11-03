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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    private String hint;

    public Word(String value, int level, String hint){
        this.value = value;
        this.level = level;
        this.hint = hint;
    }



}
