package elements.node.instructiontypes;

import elements.node.Instruction;
import elements.node.Register;

public abstract class InstructR extends Instruction {
    private Register rs;
    private Register rt;
    private Register rd;

    public InstructR(Register rs, Register rt, Register rd) {
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
        this.opcode = 0b0000000;
    }

    @Override
    public abstract void operate();

    public Register getRs() {
        return rs;
    }

    public Register getRt() {
        return rt;
    }

    public Register getRd() {
        return rd;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode() {
        opcode = 0b0100000;
    }
}
