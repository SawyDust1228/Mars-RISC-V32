package instruction;

import elements.enums.InsRegex;
import elements.enums.InsType;

import java.util.HashSet;

public class SInstructionFactory {
    private HashSet<String> SIns = new HashSet<>();

    public SInstructionFactory() {
        for (InsRegex item : InsRegex.values()) {
            if (item.getType() == InsType.S) {
                SIns.add(item.getRegex());
            }
        }
    }


}
