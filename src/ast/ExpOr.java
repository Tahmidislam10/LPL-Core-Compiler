package ast;

import compile.SymbolTable;

public class ExpOr extends Exp {

    public final Exp left, right;
    private static int labelCounter = 0; // Unique label counter

    public ExpOr(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    private String generateLabel(String base) {
        return base + "_" + (labelCounter++);
    }

    @Override
    public void compile(SymbolTable st) {
        String evalRightLabel = generateLabel("eval_right");
        String endLabel = generateLabel("end");

        left.compile(st);  // Push left operand
        emit("jumpi_z " + evalRightLabel); // If left == 0, evaluate right

        emit("push 1"); // If left is true, push 1 (short-circuit)
        emit("jumpi " + endLabel); // Skip evaluating right

        emit(evalRightLabel + ":");
        right.compile(st); // Evaluate right operand

        emit(endLabel + ":");
    }

    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
