package Bamboo.view.startup;

import Bamboo.view.resources.ResourceLoader;

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
        Button settingsBtn = new Button("btnMode.png");
        settingsBtn.addActionListener(e -> startupPanel.displaySettingsPanel());
        add(settingsBtn);

        /* Start Button */
        Button startBtn = new Button("btnStart.png");
        startBtn.addActionListener(e -> startupPanel.toggleSettingReady());
        add(startBtn);

        /* Help Button */
        Button helpBtn = new Button("btnHelp.png");
        helpBtn.addActionListener(e -> startupPanel.displayHelpPanel());
        add(helpBtn);

        setVisible(true);
    }
}
