package Bamboo.controller;

public class Mutable <T>{
    private T value;

    public Mutable(T value){
        this.value = value;
    }

    public void set(T val){this.value = val;}
    public T get(){return this.value;}
}
