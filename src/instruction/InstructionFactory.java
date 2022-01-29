package instruction;

import elements.enums.InsType;
import elements.exceptions.InstructionException;
import elements.node.Instruction;
import elements.node.Register;
import elements.token.InstructToken;
import elements.token.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class InstructionFactory {
    private RInstructionFactory rInstructionFactory;
    private IInstructionFactory iInstructionFactory;

    public InstructionFactory() {
        rInstructionFactory = new RInstructionFactory();
        iInstructionFactory = new IInstructionFactory();
    }

    public Instruction makeInstruction(ArrayList<Token> words, HashMap<Integer, Register> registerHashMap) throws InstructionException {
        if (words.get(0) instanceof InstructToken) {
            String string = words.get(0).getText();
            InsType type = whichType(string);
            switch (type) {
                case R:
                    return rInstructionFactory.makeRInstruction(words, registerHashMap);
                case I:
                    return iInstructionFactory.makeIInstruction(words, registerHashMap);
                default:
                    throw new InstructionException("make Instruction fail");
            }
        } else {
            throw new InstructionException(words.get(0));
        }

    }

    private InsType whichType(String insName) throws InstructionException {
        InsType type = InsType.WRONG_TYPE;
        type = (rInstructionFactory.isRInstruction(insName)) ? InsType.R :
                (iInstructionFactory.isIInstruction(insName)) ? InsType.I : type;
        if (type == InsType.WRONG_TYPE) {
            throw new InstructionException(type);
        }
        return type;
    }


}
