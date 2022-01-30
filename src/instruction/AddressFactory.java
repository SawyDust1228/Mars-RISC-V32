package instruction;

import elements.exceptions.ParseException;
import elements.node.Address;
import elements.token.AddressToken;
import elements.token.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AddressFactory {
    private HashSet<Address> haveMade;

    public AddressFactory() {
        haveMade = new HashSet<>();
    }

    public HashSet<Address> getHaveMade() {
        return haveMade;
    }

    public Address makeAddress(ArrayList<Token> words, HashMap<Integer, AddressToken> addressTokenHashMap) throws ParseException {
        check(words);
        assert words.get(0) instanceof AddressToken;
        Address result = new Address(words.get(0).getLineNumber(), words.get(0).getText());
        if (haveMade.contains(result)) {
            throw new ParseException("we have made the Address " + words.get(0).getText() + " we can't build it again!");
        }
        haveMade.add(result);
        return result;
    }

    public void check(ArrayList<Token> words) throws ParseException {
        if (words.size() != 2) {
            throw new ParseException("Address parse exception");
        }

        if (!words.get(0).isAddress()) {
            throw new ParseException("Address's first Token need to be an AddressToken");
        }

        if (!words.get(1).isColon()) {
            throw new ParseException("Address's second Token need to be a colon");
        }
    }
}
