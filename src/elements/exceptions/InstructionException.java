package elements.exceptions;

import elements.enums.InsType;
import elements.token.Token;

public class InstructionException extends Exception {
    public InstructionException(String msg) {
        super("Bad Instruction, we don't have Instruction " + msg);
    }

    public InstructionException(Token t) {
        super("Bad Instruction, we don't have Instruction " + t.getText());
    }

    public InstructionException(InsType insType) {
        super("Instype Wrong");
    }
}
