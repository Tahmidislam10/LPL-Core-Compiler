package handbuilt;

import ast.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Ex_a {


    public static Program buildAST() {

        List<VarDecl> decls = new ArrayList<>();
        decls.add(new VarDecl(new TypeInt(), "x"));

        List<Stm> stms = new ArrayList<>();
        stms.add(new StmAssign("x", new ExpInt(3)));
        stms.add(new StmPrint(new ExpTimes(new ExpVar("x"), new ExpInt(9))));

        return new Program(decls, stms);


    }

    public static void main(String[] args) throws IOException {
        System.out.println(buildAST());
        Program p = buildAST();
        p.compile();
        AST.write(Paths.get("ex_a.ssma"));
    }
}