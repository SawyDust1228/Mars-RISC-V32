package instruction.ins;

import elements.enums.InsRegex;
import elements.node.InstructR;
import elements.node.Register;


public class Add extends InstructR {

    public Add(Register rs, Register rt, Register rd) {
        super(rs, rt, rd);
        super.setRegex(InsRegex.add);
    }

    @Override
    public void operate() {
        super.getRd().setValue(super.getRs().getValue() + super.getRt().getValue());
    }

    @Override
    public void operate(int[] stack) {

    }

    @Override
    public String toString() {
        return super.getRs().toString() + " + " + super.getRt().toString()
                + " = " + super.getRd().toString();
    }


}
