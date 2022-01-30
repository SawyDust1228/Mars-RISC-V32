package lexer;

import elements.enums.Regex;
import elements.exceptions.ParseException;
import elements.token.*;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private LineNumberReader reader;
    private HashMap<Integer, ArrayList<Token>> words = new HashMap<>();
    private String regex = "\\s*((//.*)" + "|(" + Regex.REGISTER + ")" +
            "|(" + Regex.NUMBER + ")" + "|(" + Regex.DOT + ")" +
            "|(" + Regex.LEFT + ")" + "|(" + Regex.RIGHT + ")" + "|(" + Regex.COLON + ")" + "|(" + Regex.ADDRESS + ")" +
            "|(" + Regex.INSTRUCTION + ")" + ")?";
    private Pattern pattern = Pattern.compile(regex);
    private HashMap<Integer, AddressToken> addressTokenHashMap = new HashMap<>();

    public Lexer(Reader reader) {
        this.reader = new LineNumberReader(reader);
    }

    public Token read() throws ParseException {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }
        if (line == null) {
            return Token.EOF;
        }
        int lineNumber = reader.getLineNumber();

        parseLine(lineNumber, line);
        return Token.HAS_MORE;
    }

    private void parseLine(int index, String line) throws ParseException {
        words.put(index, new ArrayList<>());
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        while (pos < endPos) {
            matcher.region(pos, endPos);
            if (matcher.lookingAt()) {
                if (addToken(index, matcher)) {
                    pos = matcher.end();
                } else {
                    throw new ParseException("bad elements.token at line " + index);
                }
            }
        }
    }

    private boolean addToken(int index, Matcher matcher) throws ParseException {
        String m = matcher.group(1);
        if (m != null) {
            if (matcher.group(2) == null) {
                Token token;
                if (matcher.group(3) != null) {
                    token = new RegToken(index, findIndex(m));
                } else if (matcher.group(4) != null) {
                    token = new ImmeToken(index, Integer.parseInt(m));
                } else if (matcher.group(5) != null) {
                    token = new DotToken(index);
                } else if (matcher.group(6) != null) {
                    token = new BracketToken(index, true);
                } else if (matcher.group(7) != null) {
                    token = new BracketToken(index, false);
                } else if (matcher.group(8) != null) {
                    token = new ColonToken(index);
                } else if (matcher.group(9) != null) {
                    token = new AddressToken(index, m);
                    if (!addressTokenHashMap.containsValue(token)) {
                        addressTokenHashMap.put(index, (AddressToken) token);
                    }
                } else {
                    token = new InstructToken(index, m);
                }
                words.get(index).add(token);
                return true;
            }
        }
        return false;
    }


    private int findIndex(String string) throws ParseException {
        String result = "";
        int id;
        char c = string.charAt(0);
        if (c == 'a') {
            id = Integer.parseInt(string.substring(1));
            if (0 <= id && id <= 7) {
                return id + 10;
            } else {
                throw new ParseException("a register index " + id + " out of bound 7");
            }
        } else if (c == 's') {
            id = Integer.parseInt(string.substring(1));
            if (0 <= id && id <= 11) {
                if (id == 1 || id == 0) {
                    return id + 8;
                }
                return id + 16;
            } else {
                throw new ParseException("s register index " + id + " out of bound 11");
            }
        }
        if (c == 't') {
            id = Integer.parseInt(string.substring(1));
            if (0 <= id && id <= 6) {
                if (id == 0 || id == 1 || id == 2) {
                    return id + 5;
                }
                return id + 25;
            } else {
                throw new ParseException("t register index " + id + " out of bound 6");
            }
        }
        if (c == 'x') {
            id = Integer.parseInt(string.substring(1));
            if (0 <= id && id <= 31) {
                return id;
            } else {
                throw new ParseException("x register index " + id + " out of bound 31");
            }
        } else {
            throw new ParseException("register syntax wrong");
        }

    }

    public void print() {
        for (Integer index : words.keySet()) {
            for (Token t : words.get(index)) {
                System.out.println(index + " => " + t.getText());
            }
        }
    }

    public HashMap<Integer, ArrayList<Token>> getWords() {
        return words;
    }

    public HashMap<Integer, AddressToken> getAddressTokenHashMap() {
        return addressTokenHashMap;
    }
}
