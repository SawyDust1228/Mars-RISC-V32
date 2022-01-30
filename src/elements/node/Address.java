package elements.node;

public class Address extends Node {
    private String address;
    public int lineNumber;

    public Address(int lineNumber, String address) {
        this.address = address;
        this.lineNumber = lineNumber;
    }

    @Override
    public void operate() {
    }

    public String toString() {
        return address;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Address;
        if (obj.toString().equals(address)) {
            return true;
        }
        return false;
    }
}
