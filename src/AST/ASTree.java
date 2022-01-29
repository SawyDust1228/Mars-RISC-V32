package AST;

import elements.exceptions.InstructionException;
import elements.exceptions.ParseException;
import instruction.InstructionFactory;
import instruction.ins.Lw;
import instruction.ins.Sw;
import lexer.CodeDialog;
import lexer.Lexer;
import elements.node.Node;
import elements.node.Register;
import elements.token.Token;

import java.util.*;

public class ASTree {
    private static int REGNUM = 32;
    private static int MEMORY_SIZE = 1024;
    private HashMap<Integer, Register> registerHashMap = new HashMap<>();
    private Lexer lexer = new Lexer(new CodeDialog());
    private Queue<Node> forist = new LinkedList<>();
    private ArrayList<String> log = new ArrayList<>();
    private InstructionFactory factory = new InstructionFactory();
    private int[] memory = new int[MEMORY_SIZE];

    public ASTree() {
        for (int i = 0; i < REGNUM; i++) {
            registerHashMap.put(i, new Register(i));
        }
        Arrays.fill(memory, 0);
    }

    public void read() throws ParseException, InstructionException {
        while (lexer.read() != Token.EOF) {
            lexer.read();
        }
        lexer.print();
        parseWords();
    }

    private void parseWords() throws InstructionException {
        HashMap<Integer, ArrayList<Token>> words = lexer.getWords();
        for (Integer key : words.keySet()) {
            ArrayList<Token> line = lexer.getWords().get(key);
            forist.add(factory.makeInstruction(line, registerHashMap));
        }
    }

    public void operate() {
        while (!forist.isEmpty()) {
            Node tree = forist.poll();
            log.add(tree.toString());
            if (tree instanceof Lw) {
                ((Lw) tree).operate(memory);
                continue;
            }

            if (tree instanceof Sw) {
                ((Sw) tree).operate(memory);
                continue;
            }
            tree.operate();
        }
    }

    public void print() {
        System.out.println("**************");
        for (String msg : log) {
            System.out.println(msg);
        }
        System.out.println("**************");
        System.out.println("--------------");
        for (Integer key : registerHashMap.keySet()) {
            System.out.println("Reg id x" + key + ": " + registerHashMap.get(key).getValue());
        }
        System.out.println("--------------");
        System.out.println("++++++++++++++");
        for (int i = 0; i < MEMORY_SIZE; i++) {
            if (memory[i] != 0) {
                System.out.println("memory " + i + " : is " + memory[i]);
            }
        }
        System.out.println("++++++++++++++");

    }


}
