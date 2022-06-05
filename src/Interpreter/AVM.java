package Interpreter;

import Interpreter.ast.Instruction;
import Interpreter.memory.Memory;
import Interpreter.Parser.AVMParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AVM {

    public static final int CODE_SIZE = 10000;
    public static final int MEMORY_SIZE = 10000;

    private final Instruction[] code; // array of instructions
    private final Memory memory = new Memory(MEMORY_SIZE);  // stack of memory

    private int ip = 0;                 // instruction pointer, internal register, no write nor read
    private int sp = MEMORY_SIZE;       // stack pointer
    private int fp = MEMORY_SIZE -1;    // frame pointer
    private int ra;
    private int al;
    private int bsp = MEMORY_SIZE;

    private int wallet = 0;

    private final int[] a = new int[10];


    public AVM(Instruction[] code) {
        this.code = code;
    }

    public void cpu() {
        while (true) {
            if (1 >= sp) {
                System.out.println("\nAVM Error -> Stack out of memory\nHalting...");
                return;
            } else {
                Instruction bytecode = code[ip++]; // fetch instruction
                String arg1 = bytecode.getArg1();
                String arg2 = bytecode.getArg2();
                String arg3 = bytecode.getArg3();

                int offset,value;
                int address;

                try {
                    switch (bytecode.getCode()) {

                        case AVMParser.PUSH:
                            if (isRegister(arg1))
                                push(regRead(arg1));
                            else
                                push(Integer.parseInt(arg1));
                            break;

                        case AVMParser.POP:
                            if (arg1 != null && isRegister(arg1))
                                regStore(arg1, pop());
                            else
                                pop();
                            break;

                        case AVMParser.ADD:
                            sum(arg1, regRead(arg2), regRead(arg3));
                            break;

                        case AVMParser.ADDI:
                            value = Integer.parseInt(arg3);
                            sum(arg1, regRead(arg2), value);
                            break;

                        case AVMParser.SUB:
                            sub(arg1, regRead(arg2), regRead(arg3));
                            break;

                        case AVMParser.SUBI:
                            value = Integer.parseInt(arg3);
                            sub(arg1, regRead(arg2), value);
                            break;

                        case AVMParser.MULT:
                            multiplication(arg1, regRead(arg2), regRead(arg3));
                            break;

                        case AVMParser.MULTI:
                            value = Integer.parseInt(arg3);
                            multiplication(arg1, regRead(arg2), value);
                            break;

                        case AVMParser.DIV:
                            value = regRead(arg3);
                            division(arg1,regRead(arg2),value);
                            break;

                        case AVMParser.DIVI:
                            value = Integer.parseInt(arg3);
                            division(arg1,regRead(arg2),value);
                            break;

                        case AVMParser.STOREW:
                            offset = Integer.parseInt(arg2);
                            int addressStoreWord = offset + regRead(arg3);
                            memory.write(addressStoreWord, regRead(arg1));
                            //printStack();
                            break;

                        case AVMParser.LOAD:
                            value = Integer.parseInt(arg2);
                            regStore(arg1,value);
                            break;

                        case AVMParser.LOADW:
                            offset = Integer.parseInt(arg2);
                            address = offset + regRead(arg3);
                            regStore(arg1, memory.read(address));
                            break;

                        case AVMParser.MOVE:
                            value = regRead(arg1);
                            regStore(arg2, value);
                            break;

                        case AVMParser.BRANCH:
                            address = Integer.parseInt(code[ip].getArg1());
                            ip = address;
                            break;

                        case AVMParser.BCOND:
                            address = Integer.parseInt(code[ip].getArg1());
                            ip++;
                            value = regRead(bytecode.getArg1());
                            if (value == 0) ip = address;
                            break;
                        case AVMParser.JAL:
                            regStore("$ra", ip);
                            address = Integer.parseInt(code[ip].getArg1());
                            ip = address;
                            break;
                        case AVMParser.JR:
                            ip = regRead(arg1);
                            break;
                        case AVMParser.EQ:
                            regStore(arg1, regRead(arg2)==regRead(arg3)?1:0);
                            break;
                        case AVMParser.LE:
                            regStore(arg1, regRead(arg2)<=regRead(arg3)?1:0);
                            break;
                        case AVMParser.LT:
                            regStore(arg1, regRead(arg2)<regRead(arg3)?1:0);
                            break;
                        case AVMParser.GE:
                            regStore(arg1, regRead(arg2)>=regRead(arg3)?1:0);
                            break;
                        case AVMParser.GT:
                            regStore(arg1, regRead(arg2)>regRead(arg3)?1:0);
                            break;
                        case AVMParser.NOT:
                            regStore(arg1, regRead(arg2) != 0 ? 0 : 1);
                            break;
                        case AVMParser.OR:
                            regStore(arg1, (regRead(arg2)>0 || regRead(arg3)>0) ?1:0);
                            break;
                        case AVMParser.AND:
                            regStore(arg1, (regRead(arg2)>0 && regRead(arg3)>0) ?1:0);
                            break;
                        case AVMParser.PRINT:
                            System.out.println( "Print: "+ regRead(arg1));
                            break;
                        case AVMParser.TRANSFER:
                            wallet += regRead(arg1);
                            break;
                        case AVMParser.HALT:
                            System.out.println("Wallet: +" + wallet);
                            System.out.println("Halting program...");
                            return;
                    }
                } catch (Exception e) {
                    System.out.println("Program stopped at program counter: "+ip);

                    StringBuilder toPrint = new StringBuilder();
                    int cont = 0;
                    for (Instruction ins:code){
                        if(ins == null)
                            break;
                        if(cont > ip -10){
                            String literalName = AVMParser._LITERAL_NAMES[ins.getCode()];
                            String str = literalName +" "+(ins.getArg1()!=null?ins.getArg1():"") +" "+(ins.getArg2()!=null?ins.getArg2():"")+" "+(ins.getArg3()!=null?ins.getArg3():"");
                            toPrint.append(cont).append(": ").append(str).append("\n");
                            //break;
                        }
                        else if(cont == ip){
                            String literalName = AVMParser._LITERAL_NAMES[ins.getCode()];
                            String str = literalName +" "+(ins.getArg1()!=null?ins.getArg1():"") +" "+(ins.getArg2()!=null?ins.getArg2():"")+" "+(ins.getArg3()!=null?ins.getArg3():"");
                            toPrint.append(cont).append(": ").append(str).append("\n");
                            break;
                        }
                        cont++;
                    }
                    System.err.println(toPrint);
                    e.printStackTrace();

                    return;
                }
            }
        }
    }
    private boolean isRegister(String str) {
        Pattern p = Pattern.compile("\\$(([ar]\\d)|(sp)|(fp)|(al)|(ra)|(bsp))");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    private void push(int v) throws Exception {
        regStore("$sp",sp-1);
        memory.write(sp, v);
    }
    private Integer pop() throws Exception {
        Integer val = memory.read(sp);
        regStore("$sp",sp+1);

        return val;
    }

    void sum(String lhs, int first, int second) throws Exception {
        regStore(lhs, first + second);
    }
    void sub(String lhs, int first, int second) throws Exception {
        regStore(lhs, first - second);
    }
    void multiplication(String lhs, int first, int second) throws Exception {
        regStore(lhs, first * second);
    }
    void division(String lhs, int numerator, int denominator) throws Exception {
        if(denominator == 0) {
            System.err.println("Cannot divide per 0");
            System.exit(0);
        }
        regStore(lhs, numerator / denominator);
    }

    private int regRead(String reg) {

        switch (reg) {
            case "$fp":
                return fp;
            case "$bsp":
                return bsp;
            case "$al":
                return al;
            case "$sp":
                return sp;
            case "$ra":
                return ra;

            default:
                if (reg.charAt(1) == 'a') {
                    return a[Integer.parseInt(reg.substring(2))];
                }
                break;
        }
        return 0;
    }
    private void regStore(String reg, int v) throws Exception {
        switch (reg) {
            case "$fp":
                fp = v;
                break;
            case "$bsp":
                bsp = v;
                break;
            case "$al":
                al = v;
                break;
            case "$sp":
                if (v > sp) {
                    memory.cleanMemory(sp, v);
                }
                sp = v;
                if (sp <= 0) {
                    throw new Exception("Stack overflow!");
                }
                break;
            case "$ra":
                ra = v;
                break;
            default:
                if (reg.charAt(1) == 'a') {
                    a[Integer.parseInt(reg.substring(2))] = v;
                }
                break;
        }
    }

    private void printStack(){
        System.out.println("\nFP: " + fp);
        System.out.println("STACK:");
        for(int i = MEMORY_SIZE-1; i > sp ; i--){
            int cellValue;

            try {
                cellValue = memory.read(i);
            } catch (Exception e) {
                cellValue = -0;
            }
            System.out.println("\tCell " + i + " : "+ cellValue);
        }
        System.out.println("ENDSTACK\n");
    }

}