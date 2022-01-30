package instruction;

import elements.enums.InsRegex;
import elements.enums.InsType;
import elements.exceptions.InstructionException;
import elements.node.Immediate;
import elements.node.Instruction;
import elements.node.Register;
import elements.token.Token;
import instruction.ins.Lui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UInstructionFactory {

    private HashSet<String> UIns = new HashSet<>();

    public UInstructionFactory() {
        for (InsRegex item : InsRegex.values()) {
            if (item.getType() == InsType.U) {
                UIns.add(item.getRegex());
            }
        }
    }

    public Instruction makeUInstruction(ArrayList<Token> words, HashMap<Integer, Register> registerHashMap) throws InstructionException {
        String insName = words.get(0).getText();
        switch (insName) {
            case "lui":
                checkSyntax(words);
                return new Lui(registerHashMap.get(words.get(1).getId()),
                        new Immediate(words.get(3).getNumber()));
            default:
                throw new InstructionException("failed to build U Instruction");
        }
    }

    public void checkSyntax(ArrayList<Token> words) throws InstructionException {
        if (words.size() != 4) {
            throw new InstructionException("U Instruction just have 4 words");
        }
        if (!words.get(0).isInstruction()) {
            throw new InstructionException("U instruction didn't have Instruction "
                    + words.get(0).getText());
        }
        if (!words.get(1).isRegister()) {
            throw new InstructionException("U instruction: rd Register don't have "
                    + words.get(1).getText());
        }
        if (!words.get(2).isDot()) {
            throw new InstructionException("U instruction: there need to have a dot between rd Register and Immediate, but we got "
                    + words.get(2).getText());
        }
        if (!words.get(3).isImmediate()) {
            throw new InstructionException("U instruction Immediate don't have "
                    + words.get(3).getText());
        }
    }

    public boolean isUInstruction(String name) {
        return UIns.contains(name);
    }

}
