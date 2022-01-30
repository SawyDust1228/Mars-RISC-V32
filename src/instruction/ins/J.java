package instruction.ins;

import elements.node.Address;
import elements.node.instructiontypes.InstructJ;

public class J extends InstructJ {

    public J(Address address) {
        super(address);
    }

    @Override
    public void operate() {

    }

    @Override
    public void operate(int[] stack) {

    }

    public int branch() {
        return super.getLineNumber();
    }

    @Override
    public String toString() {
        return "Jump to " + super.getAddress().toString();
    }
}
