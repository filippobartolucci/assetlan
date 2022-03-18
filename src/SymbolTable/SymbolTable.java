package SymbolTable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
//allocate free lookup insert setAttribute getAttribute
public class SymbolTable {
    private Hashtable<String, List> symboltable;

    public SymbolTable() {
        this.symboltable = new Hashtable<String,List>();
    }

    public void insert(String s, String v){
        if(this.symboltable.containsKey(s)){
            this.symboltable.get(s).add(v);
        } else {
            List<String> newL = new ArrayList();
            newL.add(v);
            this.symboltable.put(s,newL);
        }
    }

    public boolean lookup(String s){
        if(this.symboltable.containsKey(s)){
            return true;
        }
        else {
            return false;
        }
    }

    public void free(){
        this.symboltable = new Hashtable<String,List>();
    }

    public List getAttribute(String s) {
        if(this.symboltable.containsKey(s)) {
            return this.symboltable.get(s);
        }
        return null;
    }

    public void printAttributes(){
        for (var entry : this.symboltable.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }

}
