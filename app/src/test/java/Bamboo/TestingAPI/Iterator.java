package Bamboo.TestingAPI;

import Bamboo.controller.Mutable;
import Bamboo.controller.heuristics.Heuristic;

import java.util.Objects;

public class Iterator<T> {
    private float min;
    private float max;
    private float step;
    private int iterations;
    private Mutable<T> reference;
    private boolean empty = false;
    private boolean numeric = true;
    private float[] values;
    private Heuristic[] non_numeric_values;

    public Iterator(float min, float max, float step){
        this.min = min;
        this.max = max;
        this.step = step;
        this.iterations = (int)Math.ceil((max-min)/step);
        buildArray();
    }

    public Iterator(Mutable<T> ref,float[] array){
        if(ref == null)this.empty = true;
        this.reference = ref;
        this.iterations = array.length;
        this.values = array;
    }

    public Iterator(Mutable<T> ref,float constant){
        if(ref == null)this.empty = true;
        this.reference = ref;
        this.iterations = 1;
        this.values = new float[]{constant};
    }

    public Iterator(String command){
        if(Objects.equals(command, "empty")){
            this.empty = true;
            this.min = 0;
            this.max = 0;
            this.step = 1;
            iterations = 1;
        }
        else{
            throw new IllegalArgumentException("Illegal command");
        }
    }

    public Iterator(Mutable<T> reference, Heuristics[]vals){
        if(reference == null)this.empty = true;
        this.reference = reference;
        this.non_numeric_values = new Heuristic[vals.length];
        for(int i = 0; i < vals.length; i++){
            non_numeric_values[i] = HeuristicsFactory.getHeuristic(vals[i]);
        }
        this.iterations = vals.length;
        this.numeric = false;
    }

    public Iterator(Mutable<T> reference, float min, float max, float step){
        if(reference == null)this.empty = true;
        this.min = min;
        this.max = max;
        this.step = step;
        this.reference = reference;
        this.iterations = (int)Math.ceil((max-min)/step) + 1;
        buildArray();
    }

    public float getStart(){return min;}
    public float getEnd(){return max;}
    public float getStep(){return step;}
    public int getArrayBounds(){
        return this.iterations;
    }
    public float[] getValues(){
        return values;
    }
    public Heuristic[] getNon_numeric_values(){return this.non_numeric_values;}
    public boolean isEmpty(){return empty;}
    public Mutable<T> getReference(){return this.reference;}
    public void setReference(Mutable<T> ref){
        this.reference = ref;
    }
    public void set(T val){
        this.reference.set(val);
    }
    public boolean isNumeric(){return this.numeric;}
    public String getStringValue(int pos){
        if(numeric)
            return Float.toString(values[pos]);
        else
            return non_numeric_values[pos].getType();
    }

    private void buildArray(){
        this.values = new float[iterations];
        int id = 0;
        for(float i = this.min; i <= max; i += step){
            this.values[id] = i;
            id++;
        }
    }
}
