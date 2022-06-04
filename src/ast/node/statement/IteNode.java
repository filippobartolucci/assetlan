package ast.node.statement;

import Semantic.GammaEnv;
import Semantic.SemanticError;
import Semantic.SigmaEnv;
import Utils.*;
import ast.node.Node;
import ast.node.StatementNode;
import ast.node.TypeNode;
import java.util.ArrayList;

public class IteNode implements Node {
    private final Node exp; // Conditional guard
    private final ArrayList<Node> thenb; // Then branch
    private final ArrayList<Node> elseb; // Else branch

    /**
     * Constructor
     */
    public IteNode(Node exp, ArrayList<Node> thenb, ArrayList<Node> elseb){
        this.exp = exp;
        this.thenb = thenb;
        this.elseb = elseb;
    }

    /**
     * Check semantic errors for this node in a given environment
     * @param env the environment
     * @return the semantic errors
     */
    public ArrayList<SemanticError> checkSemantics(GammaEnv env){
        ArrayList<SemanticError> errors = new ArrayList<>();

        if (exp != null) {
            errors.addAll(exp.checkSemantics(env));
        }

        for (Node n : thenb) {
            errors.addAll(n.checkSemantics(env));
        }

        for (Node n: elseb) {
            errors.addAll(n.checkSemantics(env));
        }

        return errors;
    }


    public Node typeCheck() {

        // \Gamma |- (e) : Bool
        if (!exp.typeCheck().equals(new TypeNode(TypeValue.BOOL))) {
            throw new RuntimeException("Type mismatch -> Condition of If statement must be of type bool");
        }

        Node thenb_type = new TypeNode(TypeValue.VOID); // Type for then branch, assuming void as default

        // \Gamma |- then : T1
        for (Node s : thenb) {  // for each statement in then branch
            Node n = ((StatementNode) s).getChild(); // getting the type of the statement
            // Looking for return statement
            if (n instanceof RetNode) {
                // return statement is found, checking its type
                if (thenb_type.equals(new TypeNode(TypeValue.VOID))) { // if true -> then branch type never updated
                    thenb_type = n.typeCheck(); // updating then branch type
                }else if (!thenb_type.equals(n.typeCheck())) { // if another return is found, checking if types are the same
                    throw new RuntimeException("Type mismatch -> Return type of If statement must be the same");
                }
            } else if (n instanceof IteNode) {
                // another if is found, if it has a type, it must be equal to this node type
                if (thenb_type.equals(new TypeNode(TypeValue.VOID))) {
                    thenb_type = n.typeCheck();
                } else if (!thenb_type.equals(n.typeCheck())) {
                    throw new RuntimeException("Type mismatch -> If statement must have the same type");
                }
            }
        }

        // same as above for else branch
        // \Gamma |- else : T2
        Node elseb_type = new TypeNode(TypeValue.VOID);

        for (Node s : elseb) {
            Node n = ((StatementNode) s).getChild();
            if (n instanceof RetNode) {
                if (elseb_type.equals(new TypeNode(TypeValue.VOID))) {
                    elseb_type = n.typeCheck();
                } else if (!elseb_type.equals(n.typeCheck())) {
                    throw new RuntimeException("Type mismatch -> Return type of If statement must be the same");
                }
            } else if (n instanceof IteNode) {
                if (elseb_type.equals(new TypeNode(TypeValue.VOID))) {
                    elseb_type = n.typeCheck();
                } else if (!elseb_type.equals(n.typeCheck())) {
                    throw new RuntimeException("Type mismatch -> If statement must have the same type");
                }
            }
        }

        // Checking if there is an else branch
        if (elseb.size() == 0) {
            // then branch might be empty too
            return thenb_type == null ? new TypeNode(TypeValue.VOID) : thenb_type;
        }

        // T1 == T2
        if (!thenb_type.equals(elseb_type)) {
            throw new RuntimeException("Type mismatch -> If statement must have the same type");
        }
        return thenb_type;
    }

    public String codeGeneration() {
        StringBuilder out = new StringBuilder();

        String lFalse = LabelManager.getFreshLabel("false");
        String lEnd = LabelManager.getFreshLabel("end");

        out.append("//ite\n");
        out.append(exp.codeGeneration());

        out.append("bc $a0 ").append(lFalse).append("\n");
        // True
        for (Node s : thenb) {
            out.append(s.codeGeneration());
        }
        out.append("b").append(" ").append(lEnd).append("\n");
        out.append(lFalse).append(":\n");
        // Else Branch
        for (Node s : elseb) {
            out.append(s.codeGeneration());
        }
        // End
        out.append(lEnd).append(":\n");

        return out.toString();
    }

    public SigmaEnv checkEffects(SigmaEnv env) {
        exp.checkEffects(env);

        SigmaEnv thenEnv = new SigmaEnv(env);
        SigmaEnv elseEnv = new SigmaEnv(env);

        for (Node n: thenb){
            thenEnv = new SigmaEnv(n.checkEffects(thenEnv));
        }

        if (elseb.size() > 0) {
            for (Node n: elseb){
                elseEnv = new SigmaEnv(n.checkEffects(elseEnv));
            }
            thenEnv.max(elseEnv);
        }


        return thenEnv;
    }

    public String toPrint(String indent){
        String s = indent + "IteNode\n";
        s += indent + "\tCONDITION:" + exp.toPrint(indent + "\t\t");
        /*//s += "\n" + indent + "\tIF:\n" + thenb.toPrint(indent + "\t");
        if (elseb!=null){
           // s +=  indent + "\tELSE:\n" + elseb.toPrint(indent + "\t");
        }*/
        return s;
    }



}



