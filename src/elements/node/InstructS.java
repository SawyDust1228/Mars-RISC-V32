package elements.node;

public abstract class InstructS extends Instruction {

    private Register rs;
    private Register rd;
    private Immediate imme;

    public InstructS(Register rs, Register rd, Immediate imme) {
        this.rs = rs;
        this.rd = rd;
        this.imme = imme;
        this.opcode = 0b0100011;

    }

    @Override
    public abstract void operate();

    public Register getRs() {
        return rs;
    }

    public Register getRd() {
        return rd;
    }

    public int getOpcode() {
        return opcode;
    }

    public Immediate getImme() {
        return imme;
    }
}
