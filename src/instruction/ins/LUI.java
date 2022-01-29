package instruction.ins;

import elements.node.Immediate;
import elements.node.Register;
import elements.node.instructiontypes.InstructU;

public class LUI extends InstructU {

    public LUI(Register rd, Immediate imme) {
        super(rd, imme);
    }

    @Override
    public void operate() {
        super.getRd().setValue((super.getImme() << 12) | ((super.getRd().getValue() << 20) >> 20));
    }

    @Override
    public void operate(int[] stack) {

    }

    @Override
    public String toString() {
        return "Load " + super.getImme() + " to " + super.getRd().toString() + "'s high 20 bit";
    }
}
