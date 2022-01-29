package elements.node;

public class Immediate extends Node {
    private int value;

    public Immediate(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "" + value;
    }

    @Override
    public void operate() {
        return;
    }
}
