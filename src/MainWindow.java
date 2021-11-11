import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MainWindow extends JFrame {
    private JPanel pMain;
    private JPanel pGraphic;
    private JTextField tfSearchName;
    private JRadioButton fRadioButton;
    private JRadioButton mRadioButton;
    private JButton searchButton;

    private final int WIDTH = 1000, HEIGHT = 550;

    public MainWindow() {

        super("Popularidad de nombres");

        ArrayList<Name> names = new ArrayList();

        try {

            Scanner scan = new Scanner(new File("names.txt"));

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);
                String name = lineScan.next();
                String sex = lineScan.next();

                ArrayList<Integer> popularity = new ArrayList<Integer>();
                while (lineScan.hasNextInt()) popularity.add(lineScan.nextInt());

                Integer[] pop = new Integer[popularity.size()];
                popularity.toArray(pop);

                names.add(new Name(name, sex, pop));
            }

        } catch (java.io.FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // System.out.println(names.get(0).getName() + ", " + names.get(0).getSex() + ", " + Arrays.toString(names.get(0).getPopularityByDecade()));

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setContentPane(pMain);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fRadioButton.setActionCommand("F");
        mRadioButton.setActionCommand("M");
        ButtonGroup sButtonGroup = new ButtonGroup();
        sButtonGroup.add(fRadioButton);
        sButtonGroup.add(mRadioButton);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = tfSearchName.getText().trim();

                try {
                    String formattedName = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
                    // System.out.println(formattedName);


                    String selectedSex = sButtonGroup.getSelection().getActionCommand();

                    // System.out.println(selectedSex);

                    Name searchName = new Name(formattedName, selectedSex, new Integer[0]);

                    int searchResult = names.indexOf(searchName);

                    if (searchResult == -1) JOptionPane.showMessageDialog(null, "No se encontró el nombre ingresado.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        // System.out.println("Sí encontré el nombre.");
                        // Pendiente: plot(names.get(searchResult));
                    }

                } catch (Exception StringIndexOutOfBoundsException) {
                    JOptionPane.showMessageDialog(null, "Entrada vacía. Por favor ingresa un nombre.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
    }

}
