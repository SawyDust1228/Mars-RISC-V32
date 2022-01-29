package elements.node.instructiontypes;

import elements.node.Immediate;
import elements.node.Instruction;
import elements.node.Register;

public abstract class InstructU extends Instruction {
    private Register rd;
    private Immediate imme;

    public InstructU(Register rd, Immediate imme) {
        this.rd = rd;
        this.imme = imme;
        this.opcode = 0b0110111;
    }

    @Override
    public abstract void operate();

    @Override
    public abstract void operate(int[] stack);

    public Register getRd() {
        return rd;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode() {
        super.opcode = 0b0010111;
    }

    public int getImme() {
        return imme.getValue();
    }
}
