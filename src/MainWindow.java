import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MainWindow extends JFrame {
    private JPanel pMain;
    private JTextField tfSearchName;
    private JRadioButton fRadioButton;
    private JRadioButton mRadioButton;
    private JButton searchButton;
    private JPanel pControl;
    private final PaintGraphic graphic;

    String input;
    String formattedName;
    String selectedSex;
    Name searchName;
    ArrayList<Name> names = GetNames("names.txt");
    Integer[] searchNamePopu;

    private final int WIDTH = 1000, HEIGHT = 550;

    public MainWindow() {

        super("Popularidad de nombres");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        graphic = new PaintGraphic();
        GridBagConstraints c = new GridBagConstraints();
        pMain.add(graphic, "Center");
        setContentPane(pMain);


        fRadioButton.setActionCommand("F");
        mRadioButton.setActionCommand("M");
        ButtonGroup sButtonGroup = new ButtonGroup();
        sButtonGroup.add(fRadioButton);
        sButtonGroup.add(mRadioButton);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                input = tfSearchName.getText().trim();
                formattedName = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
                selectedSex = sButtonGroup.getSelection().getActionCommand();

                try {
                    int searchResult = names.indexOf(new Name(formattedName, selectedSex, new Integer[0]));

                    if (searchResult == -1) JOptionPane.showMessageDialog(null, "No se encontró el nombre ingresado.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        searchName = names.get(searchResult);
                        searchNamePopu = searchName.getPopularityByDecade();
                        graphic.setList(searchNamePopu);
                        //graphic.paintComponent(new Graphics());
                    }
                }
                catch (Exception StringIndexOutOfBoundsException) {
                    JOptionPane.showMessageDialog(null, "Entrada vacía. Por favor ingresa un nombre.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public ArrayList<Name> GetNames(String pathname){
        ArrayList<Name> names = new ArrayList();

        try {

            Scanner scan = new Scanner(new File(pathname));

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);
                String name = lineScan.next();
                String sex = lineScan.next();

                ArrayList<Integer> popularity = new ArrayList<>();
                while (lineScan.hasNextInt()) popularity.add(lineScan.nextInt());

                Integer[] pop = new Integer[popularity.size()];
                popularity.toArray(pop);

                names.add(new Name(name, sex, pop));
            }

        } catch (java.io.FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return names;
    }

}
