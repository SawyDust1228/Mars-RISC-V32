package elements.node.instructiontypes;

import elements.node.Immediate;
import elements.node.Instruction;
import elements.node.Register;

public abstract class InstructS extends Instruction {

    private Register rs;
    private Register rd;
    private Immediate imme;

    public InstructS(Register rs, Register rd, Immediate imme) {
        this.rs = rs;
        this.rd = rd;
        this.imme = imme;
        this.opcode = 0b0100011;

    }

    @Override
    public abstract void operate();

    @Override
    public abstract void operate(int[] stack);

    public Register getRs() {
        return rs;
    }

    public Register getRd() {
        return rd;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getImme() {
        return imme.getValue();
    }
}
