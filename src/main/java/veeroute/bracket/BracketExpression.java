package veeroute.bracket;

import veeroute.bracket.exception.ParseException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BracketExpression {
    private List<BracketsNode> topLevelNodes = new ArrayList<>();

    private BracketExpression() {
    }

    public static BracketExpression parseExpression(String expression) throws ParseException {
        checkForIllegalSymbols(expression);

        Deque<Character> elements = new ArrayDeque<>();
        for (char symbol : expression.toCharArray()) {
            elements.addLast(symbol);
        }

        final Holder<Integer> position = new Holder<>(0);

        BracketExpression bracketExpression = new BracketExpression();
        performParseStep(bracketExpression.topLevelNodes, elements, true, position);

        return bracketExpression;
    }

    private static void performParseStep(List<BracketsNode> currLevelNodes, Deque<Character> elements, boolean isTopLevelNode, Holder<Integer> currentPosition) throws ParseException {

        while (!elements.isEmpty()) {

            assertPeekElementIsOpeningBracket(elements);

            //having opening symbol
            OpeningBracket openingBracket = OpeningBracket.getByOpeningBracketSymbol(elements.pop());
            Brackets brackets = Brackets.getByOpeningBracket(openingBracket);
            BracketsNode node = new BracketsNode(brackets);

            node.setOpeningPosition(currentPosition.getValue());
            currentPosition.setValue(currentPosition.getValue() + 1);

            //next element should be closing bracket of this node or opening bracket of the folded node
            assertNotEmpty(elements);

            //having folded node
            if (OpeningBracket.isValidOpeningBracket(elements.peek())) {
                performParseStep(node.getFoldedNodes(), elements, false, currentPosition);

                //verify not emptiness of deque after processing folded node
                assertNotEmpty(elements);
            }

            //next element must be a closing bracket and corresponds to the opening bracket
            assertPeekElementIsClosingBracket(elements);
            assertPeekElementCorrespondsToOpeningBracket(elements, brackets);

            currLevelNodes.add(node);

            //throw this closing bracket because there is no need in it anymore
            elements.pop();
            node.setClosingPosition(currentPosition.getValue());
            currentPosition.setValue(currentPosition.getValue() + 1);

            //only top level nodes can finish parsing without closing bracket
            if (elements.isEmpty()) {
                assertTopLevelNode(isTopLevelNode);
                return;
            }

            //having valid closing bracket and it is not top level node. Move one method invocation up
            if (ClosingBracket.isValidClosingBracket(elements.peek())) {
                assertNotTopLevelNode(isTopLevelNode);
                return;
            }

            //having valid opening bracket therefore let one more loop
        }
    }

    private static void assertTopLevelNode(boolean isTopLevelNode) throws ParseException {
        if (!isTopLevelNode)
            throw new ParseException("Invalid expression");
    }

    private static void assertNotTopLevelNode(boolean isTopLevelNode) throws ParseException {
        if (isTopLevelNode)
            throw new ParseException("Invalid expression");
    }

    private static void assertNotEmpty(Deque<Character> elements) throws ParseException {
        if (elements.isEmpty())
            throw new ParseException("Unexpected end of expression");
    }

    private static void assertPeekElementCorrespondsToOpeningBracket(Deque<Character> elements,
                                                                     Brackets brackets) throws ParseException {
        if (brackets.closingBracket != ClosingBracket.getByClosingBracketSymbol(elements.peek()))
            throw new ParseException("Illegal attempt to close opening bracket with not corresponding closing bracket. "
                + "Expected: " + brackets.closingBracket + ", actual: " + elements.peek());
    }

    private static void assertPeekElementIsClosingBracket(Deque<Character> elements) throws ParseException {
        if (!ClosingBracket.isValidClosingBracket(elements.peek()))
            throw new ParseException("Unexpected opening bracket: " + elements.peek());
    }

    private static void assertPeekElementIsOpeningBracket(Deque<Character> elements) throws ParseException {
        if (!OpeningBracket.isValidOpeningBracket(elements.peek()))
            throw new ParseException("Unexpected closing bracket: " + elements.peek());
    }

    private static void checkForIllegalSymbols(String expression) throws ParseException {
        for (char symbol : expression.toCharArray()) {
            if (!Brackets.isValidBracketSymbol(symbol))
                throw new ParseException("Illegal attempt to use invalid symbol: " + symbol);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        topLevelNodes.forEach(node -> node.appendNodeRepresentation(sb));

        return sb.toString();
    }

    public String toStringWithPositions() {
        StringBuilder sb = new StringBuilder();

        topLevelNodes.forEach(node -> node.appendNodeRepresentationWithPositions(sb));

        return sb.toString();
    }
}