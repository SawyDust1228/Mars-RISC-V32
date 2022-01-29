package instruction.ins;

import elements.enums.InsRegex;
import elements.node.Immediate;
import elements.node.InstructI;
import elements.node.Register;


public class Lw extends InstructI {

    public Lw(Register rs, Register rd, Immediate imme) {
        super(rs, rd, imme);
        super.setRegex(InsRegex.lw);
    }

    @Override
    public void operate() {
    }

    @Override
    public void operate(int[] stack) {
        int index = getIndex();
        super.getRd().setValue(stack[index]);
    }

    private int getIndex() {
        assert (super.getRs().getValue() + super.getImme()) % 4 == 0;
        int index = (super.getRs().getValue() + super.getImme()) / 4;
        return index;
    }

    @Override
    public String toString() {
        return "Load address: " + super.getRs().toString() + " + " + super.getImme() + " to reg " + super.getRd().toString();
    }
}
