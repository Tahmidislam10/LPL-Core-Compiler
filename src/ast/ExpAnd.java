package ast;

import compile.SymbolTable;

public class ExpAnd extends Exp {

    public final Exp left, right;
    private static int labelCounter = 0; // Unique label counter

    public ExpAnd(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    private String generateLabel(String base) {
        return base + "_" + (labelCounter++);
    }

    @Override
    public void compile(SymbolTable st) {
        String falseLabel = generateLabel("false");
        String endLabel = generateLabel("end");

        left.compile(st);
        emit("jumpi_z " + falseLabel);

        right.compile(st);
        emit("jumpi " + endLabel);

        emit(falseLabel + ":");
        emit("push 0");

        emit(endLabel + ":");
    }

    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
