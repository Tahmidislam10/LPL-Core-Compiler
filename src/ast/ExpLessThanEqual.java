package ast;

import compile.SymbolTable;

public class ExpLessThanEqual extends Exp {

    public final Exp left, right;

    public ExpLessThanEqual(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void compile(SymbolTable st) {
        left.compile(st);  // Push left operand
        right.compile(st); // Push right operand

        emit("sub");       // Compute left - right
        emit("test_n");    // Push 1 if negative (left < right), else 0
        emit("swap");      // Swap stack order

        emit("sub");       // Compute left - right again
        emit("test_z");    // Push 1 if zero (left == right), else 0
        emit("add");       // Add both results (1 if either is true)
    }

    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
