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
import instruction.ins.J;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class JInstructionFactory {

    private HashSet<String> JIns = new HashSet<>();

    public JInstructionFactory() {
        for (InsRegex item : InsRegex.values()) {
            if (item.getType() == InsType.J) {
                JIns.add(item.getRegex());
            }
        }
    }

    public Instruction makeJInstruction(ArrayList<Token> words, HashSet<Address> addresses) throws InstructionException {
        String insName = words.get(0).getText();
        switch (insName) {
            case "j":
                checkSyntax(words, addresses);
                assert words.get(1) instanceof AddressToken;
                Address a = null;
                for (Address address : addresses) {
                    if (address.toString().equals(words.get(1).getText())) {
                        a = address;
                    }
                }
                if (a == null) {
                    throw new InstructionException("fuck! build j fail");
                }

                return new J(a);
            default:
                throw new InstructionException("failed to build J Instruction");
        }
    }


    public void checkSyntax(ArrayList<Token> words, HashSet<Address> addresses) throws InstructionException {
        if (words.size() != 2) {
            throw new InstructionException("J Instruction just have 2 words");
        }
        if (!words.get(0).isInstruction()) {
            throw new InstructionException("J instruction didn't have Instruction "
                    + words.get(0).getText());
        }
        if (!words.get(1).isAddress()) {
            throw new InstructionException("J instruction: need to be an Address, but we got "
                    + words.get(1).getText());
        }


    }

    public boolean isJInstruction(String name) {
        return JIns.contains(name);
    }
}
