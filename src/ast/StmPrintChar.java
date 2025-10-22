package ast;

import compile.SymbolTable;

public class StmPrintChar extends Stm {

    public final Exp exp;

    public StmPrintChar(Exp exp) {
        this.exp = exp;
    }

    @Override
    public void compile(SymbolTable st) {
        exp.compile(st);  // Push character value onto the stack
        emit("sysc 1");   // Call system function to print character (OUT_CHAR)
    }

    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
