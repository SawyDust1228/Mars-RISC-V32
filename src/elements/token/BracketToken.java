package elements.token;

public class BracketToken extends Token {
    private boolean isLeft;

    public BracketToken(int lineNumber, boolean isLeft) {
        super(lineNumber);
        this.isLeft = isLeft;
    }

    @Override
    public boolean isBracket() {
        return true;
    }

    @Override
    public String getText() {
        return isLeft ? "(" : ")";
    }

    public boolean getIsLeft() {
        return isLeft;
    }

}
