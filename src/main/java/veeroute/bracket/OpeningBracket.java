package veeroute.bracket;

import veeroute.bracket.exception.IllegalBracketSymbolException;

enum OpeningBracket {
    SQUARE_BRACKET('['),
    ROUND_BRACKET('('),
    CURLY_BRACKET('{');

    final char symbol;

    OpeningBracket(char symbol) {
        this.symbol = symbol;
    }

    static boolean isValidOpeningBracket(char symbol) {
        for (OpeningBracket openingBracket : values()) {
            if (openingBracket.symbol == symbol)
                return true;
        }

        return false;
    }

    static OpeningBracket getByOpeningBracketSymbol(char symbol) {
        for (OpeningBracket openingBracket : values()) {
            if (openingBracket.symbol == symbol)
                return openingBracket;
        }

        throw new IllegalBracketSymbolException("Illegal opening bracket symbol: " + symbol);
    }

    @Override
    public String toString() {
        return "OpeningBracket{" + "symbol=" + symbol +
            '}';
    }
}