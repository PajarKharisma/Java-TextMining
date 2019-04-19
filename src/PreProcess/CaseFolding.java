package PreProcess;

public class CaseFolding {

    private final int UPPER = 0;
    private String input;

    public String getCaseFoldingResult(String input, int mode) {
        this.input = "";
        this.input = input;
        if (mode == UPPER) {
            return this.input.toUpperCase();
        } else {
            return this.input.toLowerCase();
        }
    }
}
