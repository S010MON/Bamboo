package Bamboo.view.startup;

import Bamboo.controller.AgentType;
import Bamboo.view.resources.Button;
import Bamboo.view.resources.Colour;
import Bamboo.view.resources.ResourceLoader;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Hashtable;

public class SettingsPanel extends JPanel
{
    private MultiConfigurationPanel multiConfigurationPanel;
    private SingleConfigurationPanel singleConfigurationPanel;
    private DemoConfigurationPanel demoConfigurationPanel ;
    private JPanel currentPanel;

    private JSlider slider;
    private JSlider slider_NN;
    private JLabel[] labelImage = new JLabel[4];
    private Mode mode = Mode.MULTI;
    private int labelImagesOffset = 2;
    private int boardSize = 5;
    private JPanel sliderPanel;
    private JPanel sliderEmptyPanel1;
    private JPanel sliderEmptyPanel2;
    private JPanel sliderPanelNN;
    private JPanel sliderEmptyPanel1NN;
    private JPanel sliderEmptyPanel2NN;
    private JPanel panel2slider;


    public SettingsPanel(StartupPanel p)
    {
        setBackground(Colour.BACKGROUND());
        setLayout(new GridLayout(4, 6));
        setVisible(true);


        slider_NN = new JSlider(JSlider.HORIZONTAL, 5, 5, boardSize) {
            @Override
            public void updateUI() {
                setUI(new CustomSliderUI(this));
            }
        };
        slider_NN.setInverted(true);


        slider = new JSlider(JSlider.HORIZONTAL, 2, 5, boardSize) {
            @Override
            public void updateUI() {
                setUI(new CustomSliderUI(this));
            }
        };
        slider.addChangeListener(e -> {
            JSlider src = (JSlider) e.getSource();
            if (src.getValueIsAdjusting())
                boardSize = slider.getValue();
            changeBoardImage2(boardSize);
        });

        slider.setPaintLabels(true);
        slider.setBackground(Colour.BACKGROUND());
        slider.setLabelTable(buildHashtableOfPositions());


        slider_NN.setPaintLabels(true);
        slider_NN.setBackground(Colour.BACKGROUND());
        slider_NN.setLabelTable(buildHashtableOfPositions());

        multiConfigurationPanel = new MultiConfigurationPanel(p);
        singleConfigurationPanel = new SingleConfigurationPanel(this,p);
        demoConfigurationPanel = new DemoConfigurationPanel(this,p);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Colour.BACKGROUND());
        buttonPanel.setLayout(null);

        Button multiBtn = new Button("btnMulti.png");
        multiBtn.setBounds(100, 50, 145, 55);
        multiBtn.addActionListener(e -> {selectMulti();
                removeSliderNN();
                changeBoardImage2(slider.getValue());});

        Button singleBtn = new Button("btnSingle.png");
        singleBtn.setBounds(250, 50, 145, 55);
        singleBtn.addActionListener(e -> {selectSingle();
            if (checkNNSingle()){
                removeSliderNN();
                changeBoardImage2(slider.getValue());}
            else{
                removeSlider();
            changeBoardImage2(5);}});

        Button demoBtn = new Button("btnDemo.png");
        demoBtn.setBounds(400, 50, 145, 55);
        demoBtn.addActionListener(e ->{ selectDemo();
            if (checkNNDemo()){
                removeSliderNN();
                changeBoardImage2(slider.getValue());}
            else{
                removeSlider();
            changeBoardImage2(5);}});

        buttonPanel.add(multiBtn);
        buttonPanel.add(singleBtn);
        buttonPanel.add(demoBtn);
        add(buttonPanel);

        sliderPanel = new JPanel() ;
        sliderPanel.setBackground(Colour.BACKGROUND());

        sliderEmptyPanel1 = new JPanel();
        sliderEmptyPanel1.setBackground(Colour.BACKGROUND());
        sliderEmptyPanel2 = new JPanel();
        sliderEmptyPanel2.setBackground(Colour.BACKGROUND());

        sliderPanel.add(slider);

        sliderPanelNN = new JPanel() ;
        sliderPanelNN.setBackground(Colour.BACKGROUND());
        sliderPanelNN.setLayout(new GridLayout(1,3));
        sliderEmptyPanel1NN = new JPanel();
        sliderEmptyPanel1NN.setBackground(Colour.BACKGROUND());
        sliderEmptyPanel2NN = new JPanel();
        sliderEmptyPanel2NN.setBackground(Colour.BACKGROUND());

        sliderPanelNN.add(sliderEmptyPanel1NN);
        sliderPanelNN.add(slider_NN);
        sliderPanelNN.add(sliderEmptyPanel2NN);
        sliderPanelNN.setVisible(false);


        panel2slider = new JPanel();
        panel2slider.setLayout(new FlowLayout(1));
        panel2slider.setBackground(Colour.BACKGROUND());
        panel2slider.add(sliderPanel);
        panel2slider.add(sliderPanelNN);

        add(buildImagePanel());
        add(panel2slider);


        currentPanel = new JPanel();
        currentPanel.setLayout(new BorderLayout());
        currentPanel.setVisible(true);
        currentPanel.setBackground(Colour.BACKGROUND());
        add(currentPanel);
        selectMulti();
    }

    public boolean checkNNDemo(){
        if (demoConfigurationPanel.getAgentType1()!=AgentType.NEURAL_NET
                &&demoConfigurationPanel.getAgentType2()!=AgentType.NEURAL_NET
                &&demoConfigurationPanel.getAgentType1()!=AgentType.HYBRID_NNMM
                &&demoConfigurationPanel.getAgentType2()!=AgentType.HYBRID_NNMM)
            return true ;
        else
            return false ;
    }

    public boolean checkNNSingle(){
        if ((singleConfigurationPanel.getAgentType()!=AgentType.NEURAL_NET)&&singleConfigurationPanel.getAgentType()!=AgentType.HYBRID_NNMM)
            return true ;
        else
            return false ;
    }
    public void removeSlider(){
        sliderPanel.setVisible(false);
        sliderPanelNN.setVisible(true);
        repaint();

    }

    public void removeSliderNN(){
       sliderPanelNN.setVisible(false);
       sliderPanel.setVisible(true);
       repaint();
    }

    private JPanel buildImagePanel()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Colour.BACKGROUND());

        labelImage[0] = new JLabel(new ImageIcon(new ResourceLoader().getImage("2.png")));
        labelImage[0].setVisible(false);
        labelImage[1] = new JLabel(new ImageIcon(new ResourceLoader().getImage("3.png")));
        labelImage[1].setVisible(false);
        labelImage[2] = new JLabel(new ImageIcon(new ResourceLoader().getImage("4.png")));
        labelImage[2].setVisible(false);
        labelImage[3] = new JLabel(new ImageIcon(new ResourceLoader().getImage("5.png")));

        panel.add(labelImage[0]);
        panel.add(labelImage[1]);
        panel.add(labelImage[2]);
        panel.add(labelImage[3]);
        return panel;
    }

    private Hashtable buildHashtableOfPositions()
    {
        Hashtable position = new Hashtable();
        position.put(2, new JLabel("2"));
        position.put(3, new JLabel("3"));
        position.put(4, new JLabel("4"));
        position.put(5, new JLabel("5"));
        position.put(6, new JLabel("6"));
        position.put(7, new JLabel("7"));
        return position;
    }

    public void changeBoardImage2(int size)
    {
        size = size - labelImagesOffset;
        for(int i = 0; i < labelImage.length; i++)
        {
            if(i != size)
                labelImage[i].setVisible(false);
        }
        labelImage[size].setVisible(true);
    }

    public int getBoardSize() {
            return boardSize;
    }

    public Mode getMode()
    {
        return mode;
    }

    public Color getPlayer1Colour()
    {
        if(getMode() == Mode.SINGLE)
            return singleConfigurationPanel.getPlayer1Color();
        if(getMode()== Mode.MULTI)
            return multiConfigurationPanel.getPlayer1Color();
        return demoConfigurationPanel.getAI1color() ;
    }

    public Color getPlayer2Colour()
    {
        if(getMode() == Mode.SINGLE)
            return singleConfigurationPanel.getAIcolor();
        if(getMode()==Mode.MULTI)
            return multiConfigurationPanel.getPlayer2Color();
        return demoConfigurationPanel.getAI2color() ;
    }

    public String getPlayer1Name()
    {
        if(getMode() == Mode.SINGLE)
            return singleConfigurationPanel.getNamePlayer1();
        return multiConfigurationPanel.getNamePlayer1();
    }

    public String getPlayer2Name()
    {
        if(getMode() == Mode.MULTI)
            return multiConfigurationPanel.getNamePlayer2();
        return "Computer";
    }

    public AgentType getAgentType()
    {
        return singleConfigurationPanel.getAgentType();
    }

    public AgentType getAgentType1(){
        return demoConfigurationPanel.getAgentType1() ;
    }

    public AgentType getAgentType2(){
        return demoConfigurationPanel.getAgentType2() ;
    }


    private void selectMulti()
    {
        mode = Mode.MULTI;
        currentPanel.setVisible(false);
        currentPanel.remove(singleConfigurationPanel);
        currentPanel.remove(demoConfigurationPanel);
        currentPanel.add(multiConfigurationPanel, BorderLayout.CENTER);
        currentPanel.setVisible(true);

    }

    private void selectSingle()
    {
        mode = Mode.SINGLE;
        currentPanel.setVisible(false);
        currentPanel.remove(multiConfigurationPanel);
        currentPanel.remove(demoConfigurationPanel);
        currentPanel.add(singleConfigurationPanel, BorderLayout.CENTER);
        currentPanel.setVisible(true);

    }

    private void selectDemo()
    {
        mode = Mode.DEMO;
        currentPanel.setVisible(false);
        currentPanel.remove(multiConfigurationPanel);
        currentPanel.remove(singleConfigurationPanel);
        currentPanel.add(demoConfigurationPanel) ;
        currentPanel.setVisible(true);
    }

    public DemoConfigurationPanel getDemoConfigurationPanel() {
        return demoConfigurationPanel;
    }

    public SingleConfigurationPanel getSingleConfigurationPanel() {
        return singleConfigurationPanel;
    }

    public MultiConfigurationPanel getMultiConfigurationPanel() {
        return multiConfigurationPanel;
    }

    public void swapToggleColor() {
        singleConfigurationPanel.swapColor();
        multiConfigurationPanel.swapColor();
        demoConfigurationPanel.swapColor();

    }

    private static class CustomSliderUI extends BasicSliderUI {

        private static final int TRACK_HEIGHT = 8;
        private static final int TRACK_WIDTH = 8;
        private static final int TRACK_ARC = 5;
        private static final Dimension THUMB_SIZE = new Dimension(12, 12);
        private final RoundRectangle2D.Float trackShape = new RoundRectangle2D.Float();

        public CustomSliderUI( JSlider b) {
            super(b);
            b.setPaintLabels(true);
            b.setPaintTicks(true);

        }

        @Override
        protected void calculateTrackRect() {
            super.calculateTrackRect();
            if (isHorizontal()) {
                trackRect.y = trackRect.y + (trackRect.height - TRACK_HEIGHT) / 2;
                trackRect.height = TRACK_HEIGHT;
            } else {
                trackRect.x = trackRect.x + (trackRect.width - TRACK_WIDTH) / 2;
                trackRect.width = TRACK_WIDTH;
            }
            trackShape.setRoundRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height, TRACK_ARC, TRACK_ARC);
        }

        @Override
        protected void calculateThumbLocation() {
            super.calculateThumbLocation();
            if (isHorizontal()) {
                thumbRect.y = trackRect.y + (trackRect.height - thumbRect.height) / 2;
            } else {
                thumbRect.x = trackRect.x + (trackRect.width - thumbRect.width) / 2;
            }
        }

        @Override
        protected Dimension getThumbSize() {
            return THUMB_SIZE;
        }

        private boolean isHorizontal() {
            return slider.getOrientation() == JSlider.HORIZONTAL;
        }

        @Override
        public void paint(final Graphics g, final JComponent c) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            super.paint(g, c);
        }

        @Override
        public void paintTrack(final Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Shape clip = g2.getClip();

            boolean horizontal = isHorizontal();
            boolean inverted = slider.getInverted();

            // Paint shadow.
            g2.setColor(new Color(170, 170 ,170));
            g2.fill(trackShape);

            // Paint track background.
            g2.setColor(new Color(200, 200 ,200));
            g2.setClip(trackShape);
            trackShape.y += 1;
            g2.fill(trackShape);
            trackShape.y = trackRect.y;

            g2.setClip(clip);

            // Paint selected track.
            if (horizontal) {
                boolean ltr = slider.getComponentOrientation().isLeftToRight();
                if (ltr) inverted = !inverted;
                int thumbPos = thumbRect.x + thumbRect.width / 2;
                if (inverted) {
                    g2.clipRect(0, 0, thumbPos, slider.getHeight());
                } else {
                    g2.clipRect(thumbPos, 0, slider.getWidth() - thumbPos, slider.getHeight());
                }

            } else {
                int thumbPos = thumbRect.y + thumbRect.height / 2;
                if (inverted) {
                    g2.clipRect(0, 0, slider.getHeight(), thumbPos);
                } else {
                    g2.clipRect(0, thumbPos, slider.getWidth(), slider.getHeight() - thumbPos);
                }
            }
            g2.setColor(Color.red);
            g2.fill(trackShape);
            g2.setClip(clip);
        }

        @Override
        public void paintThumb(final Graphics g) {
            g.setColor(Color.blue);
            g.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
        }

        @Override
        public void paintFocus(final Graphics g) {}
    }
}
