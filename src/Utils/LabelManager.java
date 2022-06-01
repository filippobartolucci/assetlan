package Utils;

public class LabelManager {
    private static int labelCount = 0;
    private static int funLabelCount = 0;

    public static String getFreshLabel(String label_name) {
        // TODO no spazio, due punti quando si mette le label, quando si salta no
        // Es.
        // LABELPIPPO:
        // b LABELPIPPO
        return "LABEL" + label_name + labelCount++;
    }

    public static String getFreshFunLabel() {
        return "Function" + funLabelCount;
    }

    public static String getEndFreshFunLabel() {
        return "endFunction" + funLabelCount++;
    }
}
