package Bamboo.view.startup;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener
{
    private JSlider slider;
    private boolean block = false;

    //TODO Put in references to useful components
    public SliderListener(JSlider slider)
    {
      this.slider=slider;
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        if(!block)
        {
            slider = new JSlider(JSlider.HORIZONTAL, 2, 5);
            slider.repaint();

        }
        else
        {
            // set the value to 5
            // set the display to 5
            slider = new JSlider(JSlider.HORIZONTAL, 5, 5);
            slider.repaint();
        }
    }

    public void block()
    {
        block = true;
    }

    public void unBlock()
    {
        block = false;
    }
}
