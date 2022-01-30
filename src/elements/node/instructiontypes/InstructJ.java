package elements.node.instructiontypes;

import elements.node.Address;
import elements.node.Instruction;

public abstract class InstructJ extends Instruction {
    private Address address;

    public InstructJ(Address address) {
        this.address = address;
    }

    @Override
    public abstract void operate();

    @Override
    public abstract void operate(int[] stack);

    public int getLineNumber() {
        return address.getLineNumber();
    }

    public Address getAddress() {
        return address;
    }
}
