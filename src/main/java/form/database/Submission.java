package form.database;

public class Submission {

    private String name;
    private String[] sectors;
    private boolean agreeToTerms;

    public Submission(String name, String[] sectors, boolean agreeToTerms) {
        this.name = name;
        this.sectors = sectors;
        this.agreeToTerms = agreeToTerms;
    }
}
