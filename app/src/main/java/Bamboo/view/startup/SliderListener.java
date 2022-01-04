package Bamboo.view.startup;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener
{
    private boolean block = false;

    //TODO Put in references to useful components
    public SliderListener()
    {

    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        if(block)
        {
            // Work as a normal slider

        }
        else
        {
            // set the value to 5
            // set the display to 5
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
