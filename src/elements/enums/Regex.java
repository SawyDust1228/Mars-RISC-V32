package elements.enums;

public enum Regex {

    INSTRUCTION("(" + InsRegex.addi.getRegex() + ")|"
            + "(" + InsRegex.sub.getRegex() + ")|"
            + "(" + InsRegex.add.getRegex() + ")|"
            + "(" + InsRegex.lw.getRegex() + ")"),
    REGISTER("[axst][0-9][0-9]?"),
    NUMBER("[+-]?[0-9]+"),
    DOT("\\,"),
    LEFT("\\("),
    RIGHT("\\)");

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
