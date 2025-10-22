package ast;

import compile.SymbolTable;
import java.util.List;

public class StmSwitch extends Stm {

    public final Exp caseExp;
    public final Stm defaultCase;
    public final List<Case> cases;
    private static int labelCounter = 0; // Unique label counter

    public StmSwitch(Exp caseExp, Stm defaultCase, List<Case> cases) {
        this.caseExp = caseExp;
        this.defaultCase = defaultCase;
        this.cases = cases;
    }

    private String generateLabel(String base) {
        return base + "_" + (labelCounter++);
    }

    @Override
    public void compile(SymbolTable st) {
        String endLabel = generateLabel("switch_end");
        String defaultLabel = (defaultCase != null) ? generateLabel("default") : endLabel;

        // Evaluate case expression (push to stack)
        caseExp.compile(st);

        // Compare caseExp against each case
        for (Case c : cases) {
            String caseLabel = generateLabel("case_" + c.caseNumber);
            emit("dup");  // Duplicate caseExp (to be used for multiple comparisons)
            emit("push " + c.caseNumber);  // Push case number
            emit("sub");  // Compare caseExp - caseNumber
            emit("test_z");  // Check if result is zero (equal)
            emit("jumpi_z " + caseLabel);  // Jump if equal

            // Case block
            emit(caseLabel + ":");
            c.stm.compile(st);
            emit("jumpi " + endLabel); // Jump to end after executing case
        }

        // If no cases matched, jump to default
        emit("jumpi " + defaultLabel);

        // Default case execution
        if (defaultCase != null) {
            emit(defaultLabel + ":");
            defaultCase.compile(st);
        }

        // End of switch statement
        emit(endLabel + ":");
    }

    public static class Case {
        public final int caseNumber;
        public final Stm stm;

        public Case(int caseNumber, Stm stm) {
            this.caseNumber = caseNumber;
            this.stm = stm;
        }
    }

    @Override
    public <T> T accept(ast.util.Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
