package instruction;

import elements.enums.InsRegex;
import elements.enums.InsType;
import elements.exceptions.InstructionException;
import elements.exceptions.ParseException;
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
                                        HashMap<Integer, AddressToken> addressTokenHashMap, HashSet<Address> addresses) throws InstructionException {
        String insName = words.get(0).getText();
        switch (insName) {
            case "beq":
                checkSyntax(words, addresses);
                assert words.get(5) instanceof AddressToken;
                Address a = null;
                for (Address address : addresses) {
                    if (address.toString().equals(words.get(5).getText())) {
                        a = address;
                    }
                }
                if (a == null) {
                    throw new InstructionException("fuck! build beq fail");
                }

                return new Beq(registerHashMap.get(words.get(1).getId()),
                        registerHashMap.get(words.get(3).getId()),
                        a);
            default:
                throw new InstructionException("failed to build B Instruction");
        }
    }

    public void checkSyntax(ArrayList<Token> words, HashSet<Address> addresses) throws InstructionException {
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
            boolean flag = false;
            for (Address address : addresses) {
                if (address.toString().equals(words.get(5).getText())) {
                    flag = true;
                }
            }
            if (flag == false) {
                throw new InstructionException("Fuck, we don't have this Address!!!!!!!");
            }
        }


    }

    public boolean isBInstruction(String name) {
        return BIns.contains(name);
    }
}
