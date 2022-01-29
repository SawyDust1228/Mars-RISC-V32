package instruction;

import elements.enums.InsRegex;
import elements.enums.InsType;
import elements.exceptions.InstructionException;
import instruction.ins.Add;
import instruction.ins.Sub;
import elements.node.Instruction;
import elements.node.Register;
import elements.token.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class RInstructionFactory {
    private HashSet<String> RIns = new HashSet<>();

    public RInstructionFactory() {
        for (InsRegex item : InsRegex.values()) {
            if (item.getType() == InsType.R) {
                RIns.add(item.getRegex());
            }
        }
    }

    public Instruction makeRInstruction(ArrayList<Token> words, HashMap<Integer, Register> registerHashMap) throws InstructionException {
        checkSyntax(words);
        String insName = words.get(0).getText();
        switch (insName) {
            case "add":
                return new Add(registerHashMap.get(words.get(3).getId()),
                        registerHashMap.get(words.get(5).getId()),
                        registerHashMap.get(words.get(1).getId()));
            case "sub":
                return new Sub(registerHashMap.get(words.get(3).getId()),
                        registerHashMap.get(words.get(5).getId()),
                        registerHashMap.get(words.get(1).getId()));
            default:
                throw new InstructionException("failed to build R Instruction");
        }
    }

    public void checkSyntax(ArrayList<Token> words) throws InstructionException {
        if (words.size() != 6) {
            throw new InstructionException("R Instruction just have 6 words");
        }
        if (!words.get(0).isInstruction()) {
            throw new InstructionException("R instruction didn't have Instruction "
                    + words.get(0).getText());
        }
        if (!words.get(1).isRegister()) {
            throw new InstructionException("R instruction: rd Register don't have "
                    + words.get(1).getText());
        }
        if (!words.get(2).isDot()) {
            throw new InstructionException("R instruction: there need to have a dot between rd Register and rs Register, but we got "
                    + words.get(2).getText());
        }
        if (!words.get(3).isRegister()) {
            throw new InstructionException("R instruction rs Register don't have "
                    + words.get(3).getText());
        }
        if (!words.get(4).isDot()) {
            throw new InstructionException("R instruction: there need to have a dot between rs Register and rt Register, but we got "
                    + words.get(4).getText());
        }

        if (!words.get(5).isRegister()) {
            throw new InstructionException("R instruction: rt Register don't have "
                    + words.get(5).getText());
        }
    }

    public boolean isRInstruction(String string) {
        return RIns.contains(string);
    }

}
