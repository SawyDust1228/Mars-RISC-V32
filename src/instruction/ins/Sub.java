package instruction.ins;

import elements.enums.InsRegex;
import elements.node.instructiontypes.InstructR;
import elements.node.Register;


public class Sub extends InstructR {

    public Sub(Register rs, Register rt, Register rd) {
        super(rs, rt, rd);
        super.setOpcode();
        super.setRegex(InsRegex.sub);
    }

    @Override
    public void operate() {
        super.getRd().setValue(super.getRs().getValue() - super.getRt().getValue());
    }

    @Override
    public void operate(int[] stack) {

    }

    @Override
    public String toString() {
        return super.getRs().toString() + " - " + super.getRt().toString()
                + " = " + super.getRd().toString();
    }

}
