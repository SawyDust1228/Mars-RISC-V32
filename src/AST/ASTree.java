package AST;

import elements.exceptions.InstructionException;
import elements.exceptions.ParseException;
import elements.token.AddressToken;
import instruction.AddressFactory;
import instruction.InstructionFactory;
import instruction.ins.Beq;
import instruction.ins.J;
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
    private HashMap<Integer, AddressToken> addressTokenHashMap;
    private int counter;
    private AddressFactory addressFactory = new AddressFactory();

    public ASTree() {
        for (int i = 0; i < REGNUM; i++) {
            registerHashMap.put(i, new Register(i));
        }
        Arrays.fill(memory, 0);
        counter = 1;
    }

    public void read() throws ParseException, InstructionException {
        while (lexer.read() != Token.EOF) {
            lexer.read();
        }
//        lexer.print();
        addressTokenHashMap = lexer.getAddressTokenHashMap();
        //这一步是找到所有的Address,先存一份便于查找
        parseWords();
    }

    private void parseWords() throws InstructionException, ParseException {
        HashMap<Integer, ArrayList<Token>> words = lexer.getWords();
        for (Integer key : words.keySet()) {
            ArrayList<Token> line = words.get(key);
            //TODO
            try {
                addressFactory.check(line);
                addressFactory.makeAddress(line, addressTokenHashMap);
            } catch (Exception ignored) {

            }
        }
    }

    private Node parseWords(int lineNumber) throws InstructionException, ParseException {
        HashMap<Integer, ArrayList<Token>> words = lexer.getWords();
        ArrayList<Token> line = words.get(lineNumber);
        return factory.makeInstruction(line, registerHashMap, addressTokenHashMap, addressFactory.getHaveMade());
    }

    private void operate(int lineNumber) throws InstructionException, ParseException {
        forist.add(parseWords(lineNumber));
    }

    public void operate() throws InstructionException, ParseException {

        if (counter >= lexer.getWords().size() + 1) {
            return;
        }
        operate(counter);
        if (!forist.isEmpty()) {
            Node tree = forist.poll();
            log.add(tree.toString());
            if (tree instanceof Lw) {
                ((Lw) tree).operate(memory);
            }

            if (tree instanceof Sw) {
                ((Sw) tree).operate(memory);
            }

            if (tree instanceof J) {
                counter = ((J) tree).branch();
                operate();
            }
            if (tree instanceof Beq) {
                int b = ((Beq) tree).branch();
                if (b == Integer.MIN_VALUE) {
                    //
                } else {
                    counter = b;
                    operate();
                }
            }
            tree.operate();
        }
        counter++;
        operate();
    }

    public void print() {
        System.out.println("**************");
        for (String msg : log) {
            System.out.println(msg);
        }
        System.out.println("**************");
        System.out.println("--------------");
        for (Integer key : registerHashMap.keySet()) {
            if(registerHashMap.get(key).getValue() != 0){
                System.out.println("Reg id x" + key + ": " + registerHashMap.get(key).getValue());
            }
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
