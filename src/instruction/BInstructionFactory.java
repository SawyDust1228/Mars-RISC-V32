package instruction;

import elements.enums.InsRegex;
import elements.enums.InsType;
import elements.exceptions.InstructionException;
import elements.node.Address;
import elements.node.Instruction;
import elements.node.Register;
import elements.token.AddressToken;
import elements.token.Token;
import instruction.ins.Beq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BInstructionFactory {

    private HashSet<String> BIns = new HashSet<>();

    public BInstructionFactory() {
        for (InsRegex item : InsRegex.values()) {
            if (item.getType() == InsType.B) {
                BIns.add(item.getRegex());
            }
        }
    }

    public Instruction makeBInstruction(ArrayList<Token> words, HashMap<Integer, Register> registerHashMap,
                                        HashMap<Integer, AddressToken> addressTokenHashMap) throws InstructionException {
        String insName = words.get(0).getText();
        switch (insName) {
            case "beq":
                checkSyntax(words, addressTokenHashMap);
                assert words.get(5) instanceof AddressToken;
                int lineNumber = -1;
                String address = null;
                for (Integer key : addressTokenHashMap.keySet()) {
                    if (addressTokenHashMap.get(key).equals(words.get(5))) {
                        lineNumber = key;
                        address = words.get(5).getText();
                        break;
                    }
                }
                if (lineNumber == -1) {
                    throw new InstructionException("failed to build B Instruction");
                }

                return new Beq(registerHashMap.get(words.get(1).getId()),
                        registerHashMap.get(words.get(3).getId()),
                        new Address(lineNumber, address));
            default:
                throw new InstructionException("failed to build B Instruction");
        }
    }

    public void checkSyntax(ArrayList<Token> words, HashMap<Integer, AddressToken> addressHashMap) throws InstructionException {
        if (words.size() != 6) {
            throw new InstructionException("B Instruction just have 6 words");
        }
        if (!words.get(0).isInstruction()) {
            throw new InstructionException("B instruction didn't have Instruction "
                    + words.get(0).getText());
        }
        if (!words.get(1).isRegister()) {
            throw new InstructionException("B instruction: rs Register don't have "
                    + words.get(1).getText());
        }
        if (!words.get(2).isDot()) {
            throw new InstructionException("B instruction: there need to have a dot between rs Register and rt Register, but we got "
                    + words.get(2).getText());
        }
        if (!words.get(3).isRegister()) {
            throw new InstructionException("B instruction rt Register don't have "
                    + words.get(3).getText());
        }
        if (!words.get(4).isDot()) {
            throw new InstructionException("B instruction: there need to have a dot between rt Register and Address, but we got "
                    + words.get(4).getText());
        }

        if (!words.get(5).isAddress()) {
            throw new InstructionException("B instruction: need to be an Address, but we got"
                    + words.get(5).getText());
        }
        if (words.get(5) instanceof AddressToken) {
            if (!addressHashMap.containsValue((AddressToken) words.get(5))) {
                throw new InstructionException("Fuck, we don't have this Address!!!!!!!");
            }
        }


    }

    public boolean isBInstruction(String name) {
        return BIns.contains(name);
    }
}
