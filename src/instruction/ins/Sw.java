package instruction.ins;

import elements.node.Immediate;
import elements.node.Register;
import elements.node.instructiontypes.InstructS;

public class Sw extends InstructS {


    public Sw(Register rs, Register rd, Immediate imme) {
        super(rs, rd, imme);
    }

    @Override
    public void operate() {

    }

    @Override
    public void operate(int[] stack) {
        int index = getIndex();
        stack[index] = super.getRd().getValue();
    }

    private int getIndex() {
        assert (super.getRs().getValue() + super.getImme()) % 4 == 0;
        int index = (super.getRs().getValue() + super.getImme()) / 4;
        return index;
    }

    @Override
    public String toString() {
        return "Store " + super.getRd().toString() + " to Address: " + super.getRs().toString() + " + " + super.getImme();
    }
}
