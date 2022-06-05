package Interpreter.ast;

import java.util.HashMap;

import Interpreter.AVM;
import Interpreter.Lexer.AVMLexer;
import Interpreter.Parser.AVMParser;

public class AVMVisitorImpl extends AVMBaseVisitor<Void> {



    private Instruction[] code = new Instruction[AVM.CODE_SIZE];
    private int i = 0;
    private HashMap<String,Integer> labelAdd = new HashMap<>();
    private HashMap<Integer,String> labelRef = new HashMap<>();

    public Instruction[] getCode() {
        return code;
    }

    @Override
    public Void visitAssembly(AVMParser.AssemblyContext ctx) {
        visitChildren(ctx);

        for (Integer refAdd: labelRef.keySet()) {
            code[refAdd] = new Instruction(AVMParser.ADDRESS, Integer.toString(labelAdd.get(labelRef.get(refAdd))));
        }
        return null;
    }

    @Override
    public Void visitInstruction(AVMParser.InstructionContext ctx) {
        switch (ctx.getStart().getType()) {
            case AVMLexer.PUSH:
                if(ctx.n != null) {
                    code[i++] = new Instruction(AVMParser.PUSH, ctx.n.getText());
                }
                else if(ctx.r1 != null){
                    code[i++] = new Instruction(AVMParser.PUSH, ctx.r1.getText());
                }
                break;
            case AVMLexer.POP:
                if (ctx.r1!=null){
                    code[i++] = new Instruction(AVMParser.POP,  ctx.r1.getText());
                }
                else{
                    code[i++] = new Instruction(AVMParser.POP);
                }
                break;
            case AVMLexer.ADD:
                code[i++] = new Instruction(AVMParser.ADD,  ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.ADDI:
                code[i++] = new Instruction(AVMParser.ADDI,  ctx.r1.getText(), ctx.r2.getText(), ctx.n.getText());
                break;
            case AVMLexer.SUB:
                code[i++] = new Instruction(AVMParser.SUB,  ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.SUBI:
                code[i++] = new Instruction(AVMParser.SUBI,  ctx.r1.getText(), ctx.r2.getText(), ctx.n.getText());
                break;
            case AVMLexer.MULT:
                code[i++] = new Instruction(AVMParser.MULT,  ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.MULTI:
                code[i++] = new Instruction(AVMParser.MULTI,  ctx.r1.getText(), ctx.r2.getText(), ctx.n.getText());
                break;
            case AVMLexer.DIV:
                code[i++] = new Instruction(AVMParser.DIV,  ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.DIVI:
                code[i++] = new Instruction(AVMParser.DIVI,  ctx.r1.getText(), ctx.r2.getText(), ctx.n.getText());
                break;
            case AVMLexer.NOT:
                code[i++] = new Instruction(AVMParser.NOT,  ctx.r1.getText(), ctx.r2.getText());
                break;
            case AVMLexer.OR:
                code[i++] = new Instruction(AVMParser.OR,  ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.AND:
                code[i++] = new Instruction(AVMParser.AND,  ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.LOAD:
                code[i++] = new Instruction(AVMParser.LOAD,  ctx.r1.getText(), ctx.n.getText());
                break;
            case AVMLexer.STOREW:
                code[i++] = new Instruction(AVMParser.STOREW,  ctx.r1.getText(), ctx.o.getText(), ctx.r2.getText());
                break;
            case AVMLexer.LOADW:
                code[i++] = new Instruction(AVMParser.LOADW,  ctx.r1.getText(), ctx.o.getText(), ctx.r2.getText());
                break;
            case AVMLexer.MOVE:
                code[i++] = new Instruction(AVMParser.MOVE, ctx.r1.getText(), ctx.r2.getText());
                break;
            case AVMLexer.LABEL:
                labelAdd.put(ctx.l.getText(), i);
                break;
            case AVMLexer.BRANCH:
                code[i++] = new Instruction(AVMParser.BRANCH, ctx.l.getText());
                labelRef.put(i++,(ctx.l!=null? ctx.l.getText():null));
                break;
            case AVMLexer.BCOND:
                code[i++] = new Instruction(AVMParser.BCOND, ctx.r1.getText(), ctx.l.getText());
                labelRef.put(i++,(ctx.l!=null? ctx.l.getText():null));
                break;
            case AVMLexer.EQ:
                code[i++] = new Instruction(AVMParser.EQ, ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.LE:
                code[i++] = new Instruction(AVMParser.LE, ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.LT:
                code[i++] = new Instruction(AVMParser.LT, ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.GE:
                code[i++] = new Instruction(AVMParser.GE, ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.GT:
                code[i++] = new Instruction(AVMParser.GT, ctx.r1.getText(), ctx.r2.getText(), ctx.r3.getText());
                break;
            case AVMLexer.JAL:
                code[i++] = new Instruction(AVMParser.JAL, ctx.l.getText());
                labelRef.put(i++,(ctx.l!=null? ctx.l.getText():null));
                break;
            case AVMLexer.JR:
                code[i++] = new Instruction(AVMParser.JR, ctx.r1.getText());
                break;
            case AVMLexer.PRINT:
                if (ctx.r1 == null)
                    code[i++] = new Instruction(AVMParser.PRINT);
                else
                    code[i++] = new Instruction(AVMParser.PRINT, ctx.r1.getText());
                break;
            case AVMLexer.TRANSFER:
                code[i++] = new Instruction(AVMParser.TRANSFER, ctx.r1.getText());
                break;

            case AVMLexer.HALT:
                code[i++] = new Instruction(AVMParser.HALT);
                break;
            default:
                break;	// Invalid instruction
        }
        return null;
    }

}