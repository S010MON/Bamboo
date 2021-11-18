package Bamboo.ExperimentationWinRates;

import java.util.Objects;

public class Iterator {
    private float min;
    private float max;
    private float step;
    private float reference;
    private boolean empty = false;

    public Iterator(float min, float max, float step){
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public Iterator(String command){
        if(Objects.equals(command, "empty")){
            this.empty = true;
        }
    }

    public Iterator(float reference, float min, float max, float step){
        this.min = min;
        this.max = max;
        this.step = step;
        this.reference = reference;
    }

    public float getStart(){return min;}
    public float getEnd(){return max;}
    public float getStep(){return step;}

    public boolean isEmpty(){return empty;}
    public float getReference(){return this.reference;}
    public void setReference(float ref){
        float temp = this.reference;
        this.reference = ref;
        this.reference = temp;
    }
}
