package elements.exceptions;

import java.io.IOException;

public class ParseException extends Exception {
    public ParseException(String string) {
        super(string);
    }

    public ParseException(IOException e) {
        super(e);
    }

}
