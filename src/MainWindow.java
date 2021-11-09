import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{
    private JTextField tfSearchName;
    private JPanel pMain;
    private JPanel pGraphic;
    private JComboBox cbSex;

    private final int WIDTH = 600, HEIGHT = 200;

    public MainWindow(){

        super("Popularidad de nombres");

        setVisible(true);
        setLocation(400,40);
        setSize(WIDTH,HEIGHT);
        setContentPane(pMain);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
