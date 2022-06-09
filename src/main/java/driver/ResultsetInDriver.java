package driver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultsetInDriver {

    void load(ResultSet rs) throws SQLException {
        rs.getDate(1).toLocalDate();
        rs.getTimestamp(1).toLocalDateTime();
    }
}
