package elements.node;

import elements.enums.InsRegex;

public abstract class Instruction extends Node {
    protected int opcode;
    private InsRegex regex;

    public Instruction() {

    }

    public abstract void operate();

    public abstract void operate(int[] stack);

    public InsRegex getRegex() {
        return regex;
    }

    public void setRegex(InsRegex regex) {
        this.regex = regex;
    }
}
