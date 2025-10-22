package handbuilt;

import ast.*;

import java.util.ArrayList;
import java.util.List;

public class Ex_c {

    public static Program buildAST() {

        List<VarDecl> decls = new ArrayList<>();
        decls.add(new VarDecl(new TypeInt(), "count"));

        List<Stm> stms = new ArrayList<>();

        stms.add(new StmAssign("count", new ExpInt(3)));


        Exp condition = new ExpLessThan(new ExpInt(0), new ExpPlus(new ExpVar("count"), new ExpInt(1)));


        List<Stm> whileBody = new ArrayList<>();
        whileBody.add(new StmPrintChar(new ExpInt(32))); // printch 32 (space)
        whileBody.add(new StmPrint(new ExpVar("count"))); // print count
        whileBody.add(new StmAssign("count", new ExpMinus(new ExpVar("count"), new ExpInt(1)))); // count = count - 1


        stms.add(new StmWhile(condition, new StmBlock(whileBody)));

        return new Program(decls, stms);
    }

    public static void main(String[] args) {
        System.out.println(buildAST());
    }
}
