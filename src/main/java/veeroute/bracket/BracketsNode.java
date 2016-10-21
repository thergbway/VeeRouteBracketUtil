package veeroute.bracket;

import java.util.ArrayList;
import java.util.List;

class BracketsNode {
    private final Brackets brackets;
    private final List<BracketsNode> foldedNodes = new ArrayList<>();

    private int openingPosition;
    private int closingPosition;

    BracketsNode(Brackets brackets) {
        this.brackets = brackets;
    }

    List<BracketsNode> getFoldedNodes() {
        return foldedNodes;
    }

    public int getOpeningPosition() {
        return openingPosition;
    }

    public void setOpeningPosition(int openingPosition) {
        this.openingPosition = openingPosition;
    }

    public int getClosingPosition() {
        return closingPosition;
    }

    public void setClosingPosition(int closingPosition) {
        this.closingPosition = closingPosition;
    }

    void appendNodeRepresentation(StringBuilder sb) {
        sb.append(brackets.openingBracket.symbol);

        foldedNodes.forEach(node -> node.appendNodeRepresentation(sb));

        sb.append(brackets.closingBracket.symbol);
    }

    void appendNodeRepresentationWithPositions(StringBuilder sb) {
        sb
            .append(brackets.name())
            .append(": '")
            .append(brackets.openingBracket.symbol)
            .append("' - ")
            .append(openingPosition);
        sb
            .append(", '")
            .append(brackets.closingBracket.symbol)
            .append("' - ")
            .append(closingPosition)
            .append("\n");

        foldedNodes.forEach(node -> node.appendNodeRepresentationWithPositions(sb));
    }
}
