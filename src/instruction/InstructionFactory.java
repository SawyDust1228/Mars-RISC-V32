package instruction;

import elements.enums.InsType;
import elements.exceptions.InstructionException;
import elements.exceptions.ParseException;
import elements.node.Address;
import elements.node.Node;
import elements.node.Register;
import elements.token.AddressToken;
import elements.token.InstructToken;
import elements.token.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class InstructionFactory {
    private RInstructionFactory rInstructionFactory;
    private IInstructionFactory iInstructionFactory;
    private SInstructionFactory sInstructionFactory;
    private UInstructionFactory uInstructionFactory;
    private BInstructionFactory bInstructionFactory;
    private AddressFactory addressFactory;
    private JInstructionFactory jInstructionFactory;

    public InstructionFactory() {
        rInstructionFactory = new RInstructionFactory();
        iInstructionFactory = new IInstructionFactory();
        sInstructionFactory = new SInstructionFactory();
        uInstructionFactory = new UInstructionFactory();
        bInstructionFactory = new BInstructionFactory();
        jInstructionFactory = new JInstructionFactory();
        addressFactory = new AddressFactory();
    }

    public Node makeInstruction(ArrayList<Token> words, HashMap<Integer, Register> registerHashMap,
                                HashMap<Integer, AddressToken> addressTokenHashMap, HashSet<Address> addresses) throws InstructionException, ParseException {
        if (words.get(0) instanceof InstructToken) {
            String string = words.get(0).getText();
            InsType type = whichType(string);
            switch (type) {
                case R:
                    return rInstructionFactory.makeRInstruction(words, registerHashMap);
                case I:
                    return iInstructionFactory.makeIInstruction(words, registerHashMap);
                case S:
                    return sInstructionFactory.makeSInstruction(words, registerHashMap);
                case U:
                    return uInstructionFactory.makeUInstruction(words, registerHashMap);
                case B:
                    return bInstructionFactory.makeBInstruction(words, registerHashMap, addressTokenHashMap, addresses);
                case J:
                    return jInstructionFactory.makeJInstruction(words, addresses);
                default:
                    throw new InstructionException("make Instruction fail");
            }
        } else if (words.get(0) instanceof AddressToken) {
            return addressFactory.makeAddress(words, addressTokenHashMap);
        } else {
            throw new InstructionException(words.get(0));
        }

    }

    private InsType whichType(String insName) throws InstructionException {
        InsType type = InsType.WRONG_TYPE;
        type = (rInstructionFactory.isRInstruction(insName)) ? InsType.R :
                (iInstructionFactory.isIInstruction(insName)) ? InsType.I :
                        (sInstructionFactory.isSInstruction(insName)) ? InsType.S :
                                (uInstructionFactory.isUInstruction(insName)) ? InsType.U :
                                        (bInstructionFactory.isBInstruction(insName)) ? InsType.B :
                                                (jInstructionFactory.isJInstruction(insName)) ? InsType.J : InsType.ADDRESS_TYPE;

        return type;
    }


}
