package veeroute.bracket;

import org.junit.Test;
import veeroute.bracket.exception.ParseException;

import static org.junit.Assert.assertEquals;

public class BracketExpressionTest {
    @Test
    public void shouldParseValidExpressions() throws ParseException {
        String exp = "{{}}[]";
        assertEquals("Invalid parse result", exp, BracketExpression.parseExpression(exp).toString());

        exp = "[{}{}]";
        assertEquals("Invalid parse result", exp, BracketExpression.parseExpression(exp).toString());

        exp = "[()][({}[])]{{}}[]";
        assertEquals("Invalid parse result", exp, BracketExpression.parseExpression(exp).toString());
    }

    @Test(expected = ParseException.class)
    public void shouldThrowExceptionOnInvalidSymbol() throws ParseException {
        String exp = "Invalid expression";

        BracketExpression.parseExpression(exp);
    }

    @Test(expected = ParseException.class)
    public void shouldThrowExceptionOnWrongFolding() throws ParseException {
        String exp = "{[}]";

        BracketExpression.parseExpression(exp);
    }

}
