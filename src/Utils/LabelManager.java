package Utils;

public class LabelManager {
    private static int labelCount = 0;

    public static String getFreshLabel(String label_name) {
        // TODO spazio blanco
        return "LABEL " + label_name + labelCount++;
    }
}
