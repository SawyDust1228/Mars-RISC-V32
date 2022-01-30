package elements.token;

public abstract class Token {
    private int lineNumber;

    public static final Token EOF = new Token(-1) {
    };
    public static final Token HAS_MORE = new Token(1) {
    };

    public Token(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean isInstruction() {
        return false;
    }

    public boolean isImmediate() {
        return false;
    }

    public boolean isRegister() {
        return false;
    }

    public boolean isDot() {
        return false;
    }

    public boolean isColon() {
        return false;
    }

    public boolean isAddress() {
        return false;
    }

    public boolean isBracket() {
        return false;
    }

    public String getText() {
        return "";
    }

    public int getId() {
        return -1;
    }

    public int getNumber() {
        return 0;
    }

}
