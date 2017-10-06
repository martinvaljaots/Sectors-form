package form.database;

public class Option {

    private int id;
    private String[] path;
    private String sector;

    public Option(int id, String[] path, String sector) {
        this.id = id;
        this.path = path;
        this.sector = sector;
    }
}
