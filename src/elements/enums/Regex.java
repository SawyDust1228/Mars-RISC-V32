package elements.enums;

public enum Regex {

    INSTRUCTION("(" + InsRegex.addi.getRegex() + ")|"
            + "(" + InsRegex.sub.getRegex() + ")|"
            + "(" + InsRegex.add.getRegex() + ")|"
            + "(" + InsRegex.lw.getRegex() + ")|"
            + "(" + InsRegex.sw.getRegex() + ")|"
            + "(" + InsRegex.lui.getRegex() + ")|"
            + "(" + InsRegex.beq.getRegex() + ")|"
            + "(" + InsRegex.jal.getRegex() + ")"),
    REGISTER("[axst][0-9][0-9]?"),
    NUMBER("[+-]?[0-9]+"),
    DOT("\\,"),
    LEFT("\\("),
    RIGHT("\\)"),
    COLON("\\:"),
    ADDRESS("[A-Z][a-z_A-Z0-9]*");

    private String regex;

    private Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public String toString() {
        return getRegex();
    }

}
