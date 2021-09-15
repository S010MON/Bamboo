package Bamboo.view.startup;

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
        setBackground(Color.GREEN);
        setSize(500,500);

        /* Settings Button */
        JButton settingsBtn = new JButton("Game");
        settingsBtn.addActionListener(e -> startupPanel.displaySettingsPanel());
        add(settingsBtn);

        /* Start Button */
        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(e -> startupPanel.toggleSettingReady());
        add(startBtn);

        /* Help Button */
        JButton helpBtn = new JButton("Help");
        helpBtn.addActionListener(e -> startupPanel.displayHelpPanel());
        add(helpBtn);

        setVisible(true);
    }
}
