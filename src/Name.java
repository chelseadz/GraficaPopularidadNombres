import java.lang.String;
import java.util.Objects;

public class Name {
    private String name;
    private String sex;
    private Integer[] popularityByDecade;

    Name(String name, String sex, Integer[] popularity) {
        this.name = name;
        this.sex = sex;
        this.popularityByDecade = popularity;
    }

    public Integer[] getPopularityByDecade() {
        return popularityByDecade;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return name.equals(name1.name) && sex.equals(name1.sex);
    }

    @Override
    public int hashCode() { return Objects.hash(name, sex); }

    public int compareTo(Name b) {
        return this.name.compareTo(b.name);
    }

}
