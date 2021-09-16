package DemoJdbcDao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDao {
	public void loginCheck() throws Exception;
	public void number() throws Exception;
	public ArrayList<Dao> findall() throws SQLException;
	public void createConn() throws SQLException;
	public void closeConn() throws SQLException;
	public void truncate() throws SQLException;	
}
