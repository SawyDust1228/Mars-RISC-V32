package elements.token;

public class AddressToken extends Token {
    private String address;

    public AddressToken(int lineNumber, String address) {
        super(lineNumber);
        this.address = address;
    }

    @Override
    public boolean isAddress() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddressToken) {
            if (((AddressToken) obj).getText().equals(this.address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getText() {
        return address;
    }
}
