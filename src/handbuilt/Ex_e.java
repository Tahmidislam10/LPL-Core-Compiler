package handbuilt;

import ast.*;

import java.util.ArrayList;
import java.util.List;

public class Ex_e {

    public static Program buildAST() {

        List<VarDecl> decls = new ArrayList<>();
        decls.add(new VarDecl(new TypeInt(), "x"));
        decls.add(new VarDecl(new TypeInt(), "zz"));

        List<Stm> stms = new ArrayList<>();

        stms.add(new StmAssign("x", new ExpMinus(new ExpVar("x"), new ExpInt(1))));
        stms.add(new StmAssign("zz", new ExpInt(55)));
        List<StmSwitch.Case> switchCases = new ArrayList<>();
        switchCases.add(new StmSwitch.Case(7, new StmPrintln(new ExpInt(99))));
        switchCases.add(new StmSwitch.Case(-1, new StmPrintln(new ExpPlus(new ExpVar("x"), new ExpVar("zz")))));
        Stm defaultCase = new StmPrintln(new ExpVar("x"));
        stms.add(new StmSwitch(new ExpVar("x"), defaultCase, switchCases));
        return new Program(decls, stms);
    }

    public static void main(String[] args) {
        System.out.println(buildAST());
    }
}
