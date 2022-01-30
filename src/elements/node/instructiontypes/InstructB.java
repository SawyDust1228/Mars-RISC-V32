package elements.node.instructiontypes;

import elements.node.Address;
import elements.node.Immediate;
import elements.node.Instruction;
import elements.node.Register;

public abstract class InstructB extends Instruction {

    private Register rs;
    private Register rt;
    private Address address;

    public InstructB(Register rs, Register rt, Address address) {
        this.rs = rs;
        this.rt = rt;
        this.address = address;
        this.opcode = 0b1100011;
    }

    @Override
    public abstract void operate();

    @Override
    public abstract void operate(int[] stack);

    public Register getRs() {
        return rs;
    }

    public Register getRt() {
        return rt;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getLineNumber() {
        return address.getLineNumber();
    }
}
