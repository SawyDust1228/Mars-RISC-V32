package instruction.ins;

import elements.node.Address;
import elements.node.Immediate;
import elements.node.Register;
import elements.node.instructiontypes.InstructB;

public class Beq extends InstructB {

    public Beq(Register rs, Register rt, Address address) {
        super(rs, rt, address);
    }

    @Override
    public void operate() {
    }

    @Override
    public void operate(int[] stack) {
    }

    public int branch() {
        if (super.getRs().getValue() == super.getRt().getValue()) {
            return super.getLineNumber();
        } else {
            return Integer.MIN_VALUE;
        }
    }

    @Override
    public String toString() {
        if (super.getRs().getValue() == super.getRt().getValue()) {
            return "" + super.getRs().toString() + " equals to " + super.getRt().toString();
        } else {
            return "" + super.getRs().toString() + " NOT equals to " + super.getRt().toString();
        }
    }
}
