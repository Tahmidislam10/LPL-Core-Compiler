package handbuilt;

import ast.*;

import java.util.ArrayList;
import java.util.List;

public class Ex_d {

    public static Program buildAST() {
        List<VarDecl> decls = new ArrayList<>();

        decls.add(new VarDecl(new TypeInt(), "x"));
        List<Stm> stms = new ArrayList<>();

        stms.add(new StmAssign("x", new ExpInt(20))); // x = 20
        Exp less20 = new ExpLessThan(new ExpVar("x"), new ExpInt(20));
        Exp less30 = new ExpLessThan(new ExpVar("x"), new ExpInt(30));

        Stm ifBranch = new StmBlock(
                List.of(new StmAssign("x", new ExpMinus(new ExpVar("x"), new ExpInt(7))))
        );

        Stm elseBranch = new StmBlock(
                List.of(new StmIf(less30,
                        new StmPrintln(new ExpInt(77)),
                        new StmPrintln(new ExpInt(88))
                ))
        );

        stms.add(new StmIf(less20, ifBranch, elseBranch));

        stms.add(new StmPrintln(new ExpVar("x")));

        return new Program(decls, stms);
    }
    public static void main(String[] args) {
        System.out.println(buildAST());
    }
}
