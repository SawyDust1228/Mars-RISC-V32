package instruction;

import elements.enums.InsRegex;
import elements.enums.InsType;
import elements.exceptions.InstructionException;
import instruction.ins.Addi;
import instruction.ins.Lw;
import elements.node.Immediate;
import elements.node.Instruction;
import elements.node.Register;
import elements.token.BracketToken;
import elements.token.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class IInstructionFactory {
    private HashSet<String> IIns = new HashSet<>();

    public IInstructionFactory() {
        for (InsRegex item : InsRegex.values()) {
            if (item.getType() == InsType.I) {
                IIns.add(item.getRegex());
            }
        }
    }

    public Instruction makeIInstruction(ArrayList<Token> words, HashMap<Integer, Register> registerHashMap) throws InstructionException {
        String insName = words.get(0).getText();
        switch (insName) {
            case "addi":
                checkSyntax(words);
                return new Addi(registerHashMap.get(words.get(3).getId()),
                        registerHashMap.get(words.get(1).getId()),
                        new Immediate(words.get(5).getNumber()));
            case "lw":
                checkSyntaxForLoad(words);
                return new Lw(registerHashMap.get(words.get(5).getId()),
                        registerHashMap.get(words.get(1).getId()),
                        new Immediate(words.get(3).getNumber()));
            default:
                throw new InstructionException("failed to build I Instruction");
        }
    }

    public void checkSyntaxForLoad(ArrayList<Token> words) throws InstructionException {
        if (words.size() != 7) {
            throw new InstructionException("Load Instruction just have 7 words");
        }
        if (!words.get(0).isInstruction()) {
            throw new InstructionException("Load(I) instruction didn't have Instruction "
                    + words.get(0).getText());
        }
        if (!words.get(1).isRegister()) {
            throw new InstructionException("Load(I) instruction: rd Register don't have "
                    + words.get(1).getText());
        }
        if (!words.get(2).isDot()) {
            throw new InstructionException("Load(I) instruction: there need to have a dot between rd Register and imme, but we got "
                    + words.get(2).getText());
        }

        if (!words.get(3).isImmediate()) {
            throw new InstructionException("Load(I) instruction: there need to have a Imme, but we got "
                    + words.get(3).getText());
        }


        if (!words.get(4).isBracket()) {
            throw new InstructionException("Load(I) instruction need to be  a bracket, but we got "
                    + words.get(4).getText());
        } else {
            if (words.get(4) instanceof BracketToken) {
                if (!((BracketToken) words.get(4)).getIsLeft()) {
                    throw new InstructionException("Load(I) instruction need to have a Left bracket, but we get ) ");
                }
            }
        }

        if (!words.get(5).isRegister()) {
            throw new InstructionException("Load(I) instruction: there need to have a rs register between brackets, but we got "
                    + words.get(5).getText());
        }

        if (!words.get(6).isBracket()) {
            throw new InstructionException("Load(I) instruction need to be a bracket, but we got "
                    + words.get(6).getText());
        } else {
            if (words.get(6) instanceof BracketToken) {
                if (((BracketToken) words.get(6)).getIsLeft()) {
                    throw new InstructionException("Load(I) instruction need to have a right bracket, but we get ( ");
                }
            }
        }


    }

    public void checkSyntax(ArrayList<Token> words) throws InstructionException {
        if (words.size() != 6) {
            throw new InstructionException("I Instruction just have 6 words");
        }
        if (!words.get(0).isInstruction()) {
            throw new InstructionException("I instruction didn't have Instruction "
                    + words.get(0).getText());
        }
        if (!words.get(1).isRegister()) {
            throw new InstructionException("I instruction: rd Register don't have "
                    + words.get(1).getText());
        }
        if (!words.get(2).isDot()) {
            throw new InstructionException("I instruction: there need to have a dot between rd Register and rs Register, but we got "
                    + words.get(2).getText());
        }
        if (!words.get(3).isRegister()) {
            throw new InstructionException("I instruction rs Register don't have "
                    + words.get(3).getText());
        }
        if (!words.get(4).isDot()) {
            throw new InstructionException("I instruction: there need to have a dot between rs Register and Immediate, but we got "
                    + words.get(4).getText());
        }

        if (!words.get(5).isImmediate()) {
            throw new InstructionException("I instruction: need to be an Immediate"
                    + words.get(5).getText());
        }
    }

    public boolean isIInstruction(String name) {
        return IIns.contains(name);
    }
}
