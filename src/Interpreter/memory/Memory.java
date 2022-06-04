package Interpreter.memory;

public class Memory {
    private final Cell[] mem;

    public Memory(int size){
        mem = new Cell[size];
        for (int i=0; i< size; i++) {
            mem[i]= new Cell();
        }
    }

    public int read(int n){
        return mem[n].read();
    }

    public void write(int add, int val){
        mem[add].write(val);
    }


    public void cleanMemory(int start, int end) {
        for (int indexStack = start;indexStack < end; indexStack++){
            mem[indexStack] = new Cell();
        }
    }
}