package Bamboo.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar{
    private JMenu fileMenu;
    private JMenuItem saveBtn;
    private JMenuItem loadBtn;
    private JMenuItem quitBtn;


    public MenuBar(MainFrame mainFrame) {

         fileMenu = new JMenu("Menu");

         /* Save button */
         saveBtn = new JMenuItem("Save");
         saveBtn.addActionListener( e -> mainFrame.save());
         fileMenu.add(saveBtn);

         /* Load button */
        loadBtn = new JMenuItem("Load");
        loadBtn.addActionListener( e -> mainFrame.save());
        fileMenu.add(loadBtn);

         /* Quit button */
         quitBtn = new JMenuItem("Quit");
         quitBtn.addActionListener( e -> mainFrame.quit());
         fileMenu.add(quitBtn);

         add(fileMenu);
         setVisible(true);
    }
}
