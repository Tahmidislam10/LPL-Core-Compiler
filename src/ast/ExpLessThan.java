package ast;

import compile.SymbolTable;

public class ExpLessThan extends Exp {

    public final Exp left, right;

    public ExpLessThan(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void compile(SymbolTable st) {
        left.compile(st);  // Push left operand
        right.compile(st); // Push right operand
        emit("sub");       // Compute left - right
        emit("test_n");    // Push 1 if negative (left < right), else 0
    }

    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
