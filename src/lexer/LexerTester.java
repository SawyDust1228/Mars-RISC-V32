package lexer;

import elements.token.Token;

public class LexerTester {
    public static void main(String[] args) throws Exception {

        Lexer lexer = new Lexer(new CodeDialog());
        while (lexer.read() != Token.EOF) {
            lexer.read();
            lexer.print();
        }

    }
}
