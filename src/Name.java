import java.lang.String;

public class Name {
    private String name;
    private boolean sex; // false es femenino y true masculino.
    private int[] popularityByDecade;

    Name(String name, boolean sex, int[] popularity) {
        this.name = name;
        this.sex = sex;
        this.popularityByDecade = popularity;
    }

    public int[] getPopularityByDecade() {
        return popularityByDecade;
    }

    public boolean isSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Name b) {
        return this.name.compareTo(b.name);
    }

}
