package instruction.ins;

import elements.enums.InsRegex;
import elements.node.Immediate;
import elements.node.instructiontypes.InstructI;
import elements.node.Register;


public class Addi extends InstructI {
    public Addi(Register rs, Register rd, Immediate imme) {
        super(rs, rd, imme);
        super.setRegex(InsRegex.addi);
    }

    @Override
    public void operate() {
        super.getRd().setValue(super.getRs().getValue() + super.getImme());
    }

    @Override
    public void operate(int[] stack) {
    }

    @Override
    public String toString() {
        return super.getRs().toString() + " + " + super.getImme() + " = " + super.getRd().toString();
    }
}
