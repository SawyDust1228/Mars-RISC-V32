package elements.node;

public class Register extends Node {
    private int value = 0;
    private int id;

    public Register(int id) {
        this.id = id;
    }

    public void setValue(int value) {
        if (id == 0) {
            return;
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void operate() {
        return;
    }

    @Override
    public String toString() {
        return "x" + id;
    }

}
