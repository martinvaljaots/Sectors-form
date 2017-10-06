package form.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    private final String url = "jdbc:postgresql://localhost:5432/forminfodb";
    private final String user = "dbadmin";
    private final String password = "d4tabasepassword";
    private String SQLInsert = "INSERT INTO formsubmissions(id, name, sectors, agreetoterms) VALUES(DEFAULT,?,?,?) " +
            "RETURNING id";
    private final String SQLOptionsQuery = "SELECT * FROM formoptions";
    private final String SQLUserQuery = "SELECT id, name, sectors, agreetoterms FROM formsubmissions ORDER BY id " +
            "DESC LIMIT 1";
    private final String SQLUpdate = "UPDATE formsubmissions SET name = ?, sectors = ?, agreetoterms = ? WHERE id = "
            + lastId + " RETURNING id";
    private static int lastId;

    public void insertOrUpdateDatabaseSubmission(String name, String[] sectors, boolean agreeToTerms,
                                                 boolean insertOrUpdate) {
        String SQLStatement;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            if (!insertOrUpdate) {
                SQLStatement = SQLUpdate;
            } else SQLStatement = SQLInsert;
            PreparedStatement pstmt = conn.prepareStatement(SQLStatement);
            pstmt.setString(1, name);
            Array sectorsArray = conn.createArrayOf("text", sectors);
            pstmt.setArray(2, sectorsArray);
            pstmt.setBoolean(3, agreeToTerms);
            pstmt.execute();

            ResultSet lastUpdatedEntry = pstmt.getResultSet();
            lastUpdatedEntry.next();
            lastId = lastUpdatedEntry.getInt(1);
            System.out.println(lastId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Option> retrieveOptionsFromDatabase() {
        List<Option> optionList = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(SQLOptionsQuery);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Array pathArray = rs.getArray("path");
                String[] path = (String[]) pathArray.getArray();
                String sector = rs.getString("sector");

                Option option = new Option(id, path, sector);
                optionList.add(option);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return optionList;
    }

    public Submission retrieveUserSubmissionFromDatabase() {

        Submission submission = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(SQLUserQuery);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String name = rs.getString("name");
            Array sectorArray = rs.getArray("sectors");
            String[] sectors = (String[]) sectorArray.getArray();
            boolean agreeToTerms = rs.getBoolean("agreetoterms");

            submission = new Submission(name, sectors, agreeToTerms);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return submission;
    }
}
