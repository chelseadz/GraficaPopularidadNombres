import java.lang.String;

public class Name {
    private String name;
    private boolean sex;     /* false es femenino y true masculino.*/
    private Integer[] popularityByDecade;

    Name(String name, boolean sex, Integer[] popularity) {
        this.name = name;
        this.sex = sex;
        this.popularityByDecade = popularity;
    }

    public Integer[] getPopularityByDecade() {
        return popularityByDecade;
    }

    public boolean getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Name b) {
        return this.name.compareTo(b.name);
    }

}
