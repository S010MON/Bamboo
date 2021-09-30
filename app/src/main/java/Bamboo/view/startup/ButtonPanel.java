package Bamboo.view.startup;

import Bamboo.view.resources.Button;

import javax.swing.*;
import java.awt.*;

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
        Bamboo.view.resources.Button settingsBtn = new Bamboo.view.resources.Button("btnMode.png");
        settingsBtn.addActionListener(e -> startupPanel.displaySettingsPanel());
        add(settingsBtn);

        /* Help Button */
        Bamboo.view.resources.Button helpBtn = new Bamboo.view.resources.Button("btnHelp.png");
        helpBtn.addActionListener(e -> startupPanel.displayHelpPanel());
        add(helpBtn);

        /* Load Button */
        Bamboo.view.resources.Button loadBtn = new Bamboo.view.resources.Button("btnLoad.png");
        loadBtn.addActionListener(e -> startupPanel.getMainFrame().load());
        add(loadBtn);

        /* Start Button */
        Bamboo.view.resources.Button startBtn = new Bamboo.view.resources.Button("btnStart.png");
        startBtn.addActionListener(e -> startupPanel.startGame());
        add(startBtn);

        /* Quit Button */
        Bamboo.view.resources.Button quitBtn = new Button("btnQuit.png");
        quitBtn.addActionListener(e -> startupPanel.getMainFrame().quitProgram());
        add(quitBtn);

        setVisible(true);
    }
}
