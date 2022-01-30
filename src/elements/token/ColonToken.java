package elements.token;

public class ColonToken extends Token {

    public ColonToken(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public boolean isColon() {
        return true;
    }

    @Override
    public String getText() {
        return ":";
    }
}
