package Interpreter.memory;

public class Cell {
    private Integer val;

    Cell(){
        val = null;
    }

    public void write(int v){
        val = v;
    }

    public Integer read() {
        if(val == null){
            System.err.println("\nValue is not written in memory.\nExit program...");
            System.exit(1);
        }
        return val;
    }

}