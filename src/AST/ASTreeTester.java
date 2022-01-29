package AST;

import elements.exceptions.InstructionException;
import elements.exceptions.ParseException;

public class ASTreeTester {
    public static void main(String[] args) throws InstructionException, ParseException {
        ASTree asTree = new ASTree();
        asTree.read();
        asTree.operate();
        asTree.print();
    }
}
