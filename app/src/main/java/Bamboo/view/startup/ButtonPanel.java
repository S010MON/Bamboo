package Bamboo.view.startup;

import Bamboo.view.resources.Button;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ButtonPanel extends JPanel
{
    private StartupPanel startupPanel;
    private LayoutManager grid;
    private JButton startBtn;
    private JButton helpBtn;
    private JButton settingsBtn;

    public ButtonPanel(StartupPanel startupPanel)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(new Color(158, 208, 239));
        setSize(500,500);

        /* Settings Button */
        Button settingsBtn = new Button("btnMode.png");
        settingsBtn.addActionListener(e -> startupPanel.displaySettingsPanel());
        add(settingsBtn);

        /* Help Button */
        Button helpBtn = new Button("btnHelp.png");
        helpBtn.addActionListener(e -> startupPanel.displayHelpPanel());
        add(helpBtn);

        /* Load Button */
        Button loadBtn = new Button("btnLoad.png");
        loadBtn.addActionListener(e -> startupPanel.getMainFrame().load());
        add(loadBtn);

        /* Start Button */
        Button startBtn = new Button("btnStart.png");
        startBtn.addActionListener(e -> {
            try {
                startupPanel.startGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        add(startBtn);

        /* Quit Button */
        Button quitBtn = new Button("btnQuit.png");
        quitBtn.addActionListener(e -> startupPanel.getMainFrame().quitProgram());
        add(quitBtn);

        setVisible(true);
    }
}
