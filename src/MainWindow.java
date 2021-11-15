import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public class MainWindow extends JFrame {
    private JPanel pMain;
    private JTextField tfSearchName;
    private JRadioButton fRadioButton;
    private JRadioButton mRadioButton;
    private JButton searchButton;
    private JPanel pControl;
    private final PaintGraphic graphic;

    private String input;
    private String formattedName;
    private String selectedSex;
    private Name searchName;
    private ArrayList<Name> names = GetNames("names.txt");
    private Integer[] searchNamePopu;
    private ButtonGroup sButtonGroup;

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
        sButtonGroup = new ButtonGroup();
        sButtonGroup.add(fRadioButton);
        sButtonGroup.add(mRadioButton);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintGraph();
            }
        });

        tfSearchName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if (e.getKeyCode() == 10 ) { // Enter pressed
                    paintGraph();
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

    private void paintGraph() {

        input = tfSearchName.getText().trim();
        formattedName = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        selectedSex = sButtonGroup.getSelection().getActionCommand();
        try {
            int searchResult = names.indexOf(new Name(formattedName, selectedSex, new Integer[0]));

            if (searchResult == -1) JOptionPane.showMessageDialog( pMain, "No se encontró el nombre ingresado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            else {
                searchName = names.get(searchResult);
                searchNamePopu = searchName.getPopularityByDecade();
                graphic.setList(searchNamePopu);
                graphic.repaint();
                graphic.setVisible(false);
                graphic.setVisible(true);
            }
        }
        catch (Exception StringIndexOutOfBoundsException) {
            JOptionPane.showMessageDialog(null, "Entrada vacía. Por favor ingresa un nombre.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
