package elements.token;

public class ImmeToken extends Token {
    private int value;

    public ImmeToken(int lineNumber, int value) {
        super(lineNumber);
        this.value = value;
    }

    @Override
    public boolean isImmediate() {
        return true;
    }

    public int getNumber() {
        return this.value;
    }

    @Override
    public String getText() {
        String result = "";
        return result + value;
    }
}
