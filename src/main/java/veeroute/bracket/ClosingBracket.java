package veeroute.bracket;

import veeroute.bracket.exception.IllegalBracketSymbolException;

enum ClosingBracket {
    SQUARE_BRACKET(']'),
    ROUND_BRACKET(')'),
    CURLY_BRACKET('}');

    final char symbol;

    ClosingBracket(char symbol) {
        this.symbol = symbol;
    }

    static boolean isValidClosingBracket(char symbol) {
        for (ClosingBracket closingBracket : values()) {
            if (closingBracket.symbol == symbol)
                return true;
        }

        return false;
    }

    static ClosingBracket getByClosingBracketSymbol(char symbol) {
        for (ClosingBracket closingBracket : values()) {
            if (closingBracket.symbol == symbol)
                return closingBracket;
        }

        throw new IllegalBracketSymbolException("Illegal closing bracket symbol: " + symbol);
    }

    @Override
    public String toString() {
        return "ClosingBracket{" + "symbol=" + symbol +
            '}';
    }
}
