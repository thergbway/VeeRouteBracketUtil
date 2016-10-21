package veeroute.bracket.exception;

import org.jetbrains.annotations.NonNls;

public class IllegalBracketSymbolException extends RuntimeException {
    public IllegalBracketSymbolException() {
    }

    public IllegalBracketSymbolException(@NonNls String message) {
        super(message);
    }

    public IllegalBracketSymbolException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalBracketSymbolException(Throwable cause) {
        super(cause);
    }
}