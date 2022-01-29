package instruction;

import elements.enums.InsRegex;
import elements.enums.InsType;
import elements.exceptions.InstructionException;
import elements.node.Immediate;
import elements.node.Instruction;
import elements.node.Register;
import elements.token.BracketToken;
import elements.token.Token;
import instruction.ins.Sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SInstructionFactory {
    private HashSet<String> SIns = new HashSet<>();

    public SInstructionFactory() {
        for (InsRegex item : InsRegex.values()) {
            if (item.getType() == InsType.S) {
                SIns.add(item.getRegex());
            }
        }
    }

    public Instruction makeSInstruction(ArrayList<Token> words, HashMap<Integer, Register> registerHashMap) throws InstructionException {
        String insName = words.get(0).getText();
        switch (insName) {
            case "sw":
                checkSyntax(words);
                return new Sw(registerHashMap.get(words.get(5).getId()),
                        registerHashMap.get(words.get(1).getId()),
                        new Immediate(words.get(3).getNumber()));
            default:
                throw new InstructionException("failed to build S Instruction");
        }
    }

    public void checkSyntax(ArrayList<Token> words) throws InstructionException {
        if (words.size() != 7) {
            throw new InstructionException("Store(S) Instruction just have 7 words");
        }
        if (!words.get(0).isInstruction()) {
            throw new InstructionException("Store(S) instruction didn't have Instruction "
                    + words.get(0).getText());
        }
        if (!words.get(1).isRegister()) {
            throw new InstructionException("Store(S) instruction: rd Register don't have "
                    + words.get(1).getText());
        }
        if (!words.get(2).isDot()) {
            throw new InstructionException("Store(S) instruction: there need to have a dot between rd Register and imme, but we got "
                    + words.get(2).getText());
        }

        if (!words.get(3).isImmediate()) {
            throw new InstructionException("Store(S) instruction: there need to have a Imme, but we got "
                    + words.get(3).getText());
        }


        if (!words.get(4).isBracket()) {
            throw new InstructionException("Store(S) instruction need to be  a bracket, but we got "
                    + words.get(4).getText());
        } else {
            if (words.get(4) instanceof BracketToken) {
                if (!((BracketToken) words.get(4)).getIsLeft()) {
                    throw new InstructionException("Store(S) instruction need to have a Left bracket, but we get ) ");
                }
            }
        }

        if (!words.get(5).isRegister()) {
            throw new InstructionException("Store(S) instruction: there need to have a rs register between brackets, but we got "
                    + words.get(5).getText());
        }

        if (!words.get(6).isBracket()) {
            throw new InstructionException("Store(S) instruction need to be a bracket, but we got "
                    + words.get(6).getText());
        } else {
            if (words.get(6) instanceof BracketToken) {
                if (((BracketToken) words.get(6)).getIsLeft()) {
                    throw new InstructionException("Store(S) instruction need to have a right bracket, but we get ( ");
                }
            }
        }


    }

    public boolean isSInstruction(String name) {
        return SIns.contains(name);
    }

}
