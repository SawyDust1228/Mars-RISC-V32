package elements.enums;

public enum InsRegex {
    add("add", InsType.R),
    sub("sub", InsType.R),
    addi("addi", InsType.I),
    lw("lw", InsType.I),
    sw("sw", InsType.S),
    lui("lui", InsType.U),
    AUIPC("AUIPC", InsType.U),
    beq("beq", InsType.B),
    j("j", InsType.J);
    private String regex;
    private InsType type;

    private InsRegex(String regex, InsType type) {
        this.regex = regex;
        this.type = type;
    }

    public String getRegex() {
        return regex;
    }

    public InsType getType() {
        return type;
    }


}
