package ast;

import compile.SymbolTable;

public class StmIf extends Stm {

    public final Exp exp;

    public final Stm trueBranch, falseBranch;

    public StmIf(Exp exp, Stm trueBranch, Stm falseBranch) {
        this.exp = exp;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    @Override
    public void compile(SymbolTable st) {
        String elseLabel = st.freshLabel("else");
        String endLabel = st.freshLabel("endif");
        exp.compile(st);
        emit("jumpi_z " + elseLabel);
        trueBranch.compile(st);
        emit("jumpi " + endLabel);
        emit(elseLabel + ":");
        falseBranch.compile(st);
        emit(endLabel + ":");
    }

    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) { return visitor.visit(this); }
}
