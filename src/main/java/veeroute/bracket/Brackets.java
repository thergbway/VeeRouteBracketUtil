package veeroute.bracket;

import veeroute.bracket.exception.IllegalBracketSymbolException;

enum Brackets {
    ROUND_BRACKETS(OpeningBracket.ROUND_BRACKET, ClosingBracket.ROUND_BRACKET),
    CURLY_BRACKETS(OpeningBracket.CURLY_BRACKET, ClosingBracket.CURLY_BRACKET),
    SQUARE_BRACKETS(OpeningBracket.SQUARE_BRACKET, ClosingBracket.SQUARE_BRACKET);

    final OpeningBracket openingBracket;
    final ClosingBracket closingBracket;

    Brackets(OpeningBracket openingBracket, ClosingBracket closingBracket) {
        this.openingBracket = openingBracket;
        this.closingBracket = closingBracket;
    }

    static boolean isValidBracketSymbol(char symbol) {
        return OpeningBracket.isValidOpeningBracket(symbol) || ClosingBracket.isValidClosingBracket(symbol);
    }

    static Brackets getByOpeningBracket(OpeningBracket openingBracket) {
        for (Brackets brackets : values()) {
            if (brackets.openingBracket == openingBracket)
                return brackets;
        }

        throw new IllegalBracketSymbolException("Illegal opening bracket: " + openingBracket);
    }

    @Override
    public String toString() {
        return "Brackets{" + "openingBracket=" + openingBracket +
            ", closingBracket=" + closingBracket +
            '}';
    }
}