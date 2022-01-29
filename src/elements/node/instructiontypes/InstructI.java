package elements.node.instructiontypes;

import elements.node.Immediate;
import elements.node.Instruction;
import elements.node.Register;

public abstract class InstructI extends Instruction {

    private Register rs;
    private Register rd;
    private Immediate imme;

    public InstructI(Register rs, Register rd, Immediate imme) {
        this.rd = rd;
        this.rs = rs;
        this.imme = imme;
        this.opcode = 0b0010011;
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

    public void setOpcode() {
        super.opcode = 0b0000011;
    }

    public int getImme() {
        return imme.getValue();
    }
}
