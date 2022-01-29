package elements.token;

public class RegToken extends Token {
    private int id;

    public RegToken(int lineNumber, int id) {
        super(lineNumber);
        this.id = id;
    }

    @Override
    public boolean isRegister() {
        return true;
    }

    @Override
    public String getText() {
        return "x" + id;
    }

    public int getId() {
        return id;
    }
}
