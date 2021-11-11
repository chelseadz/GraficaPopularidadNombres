/*
Integrantes:
Durazo Duarte Chelsea
Felix Morales Jose Heriberto
Torres Gonzalez Diego
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        MainWindow main = new MainWindow();

        ArrayList<Name> names = new ArrayList();

        try {
            Scanner file = new Scanner(new File("names.txt"));

            while (file.hasNextLine() ) {
                Scanner nextLine = new Scanner(file.nextLine());

                if (nextLine.hasNext()) {
                    String name = nextLine.next();
                    boolean sex = Objects.equals(nextLine.next(), "M");

                    ArrayList<Integer> popularity = new ArrayList<Integer>();

                    while (nextLine.hasNextInt()) popularity.add(nextLine.nextInt());

                    Integer[] popu = new Integer[popularity.size()];
                    popularity.toArray(popu);

                    names.add(new Name(name, sex, popu));
                }

            }

        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error al abrir el archivo.");
        }

        System.out.println(names.get(0).getName() + ", " + names.get(0).getSex() + ", " +
                Arrays.toString(names.get(0).getPopularityByDecade()));
    }
}
