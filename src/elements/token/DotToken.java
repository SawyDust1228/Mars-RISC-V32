package elements.token;

public class DotToken extends Token {

    public DotToken(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public boolean isDot() {
        return true;
    }

    @Override
    public String getText() {
        return ",";
    }
}
