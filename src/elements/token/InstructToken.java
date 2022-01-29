package elements.token;

public class InstructToken extends Token {
    private String instruction;

    public InstructToken(int lineNumber, String instruction) {
        super(lineNumber);
        this.instruction = instruction;
    }

    @Override
    public boolean isInstruction() {
        return true;
    }

    @Override
    public String getText() {
        return instruction;
    }
}

