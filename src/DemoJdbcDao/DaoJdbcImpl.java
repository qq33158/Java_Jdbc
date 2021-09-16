package DemoJdbcDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DaoJdbcImpl implements IDao{

	private Connection conn;
	
	@Override
	public void loginCheck() throws  Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username");
		String userName = sc.nextLine();
		System.out.println("Enter password");
		String passWord = sc.nextLine(); 
	
		boolean status = checkUser(userName, passWord);
		if(status == true) {
			System.out.println("帳號密碼輸入正確");	
		}else {
			System.out.println("帳號密碼輸入錯誤");
			System.out.println("是否繼續程式?(YES/NO)");
			String str = sc.nextLine();
			if(str.equals("YES") | str.equals("yes") | str.equals("Yes") | str.equals("Y")| str.equals("y")) {
				loginCheck();
			} else {
				System.exit(0);
			}
		}
	}
	private boolean checkUser(String userName, String passWord) throws Exception {
		String sqlstr = "SELECT * FROM users WHERE username=? and password=?";
		
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setString(1, userName);
		state.setString(2, passWord);
		
		ResultSet rs = state.executeQuery();
		boolean check= rs.next();
		
		rs.close();
		state.close();
		return check;
	}
	
	public static int[] count(int[] array, int maxValue) { 
		int[] count = new int[maxValue + 1]; 
		for (int n : array) count[n]++;
		return count; 
	}
	
	@Override
	public void truncate() throws SQLException {
		String sqldel = "Truncate number";
		PreparedStatement state =conn.prepareStatement(sqldel);
		state.executeUpdate();
		state.close();
		//System.out.println("已重設資料庫");		
	}
	
	@Override
	public void number() throws Exception {		
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("請輸入要幾組");		
			//System.out.println("Set is "+set);
			int set  = sc.nextInt();
			for(int i = 0; i < set; i++) {
				List<Integer> list = new ArrayList<Integer>();
				List<Integer> list1 = new ArrayList<Integer>();
				
				Random rand = new Random(); 
				int[] array = new int[1000000]; 
				
				for (int j = 0; j < array.length; j++) array[j] = rand.nextInt(42);
				int[] count = count(array, 42); 
				
				for (int j = 0; j < count.length; j++) {
					list.add(count[j]);
				}
				for(int k = 0; k < 6; k++) {
					int numbers = Collections.max(list);
					list1.add(list.indexOf(numbers)+1);
					list.set(list.indexOf(numbers),0);				
				}
				Collections.sort(list1);			
				// System.out.println(list1);
				
				String sqlstr = "INSERT INTO number(number1,number2,number3,number4,number5,number6) VALUES(?,?,?,?,?,?)";
				PreparedStatement state =conn.prepareStatement(sqlstr);
				state.setInt(1, list1.get(0));
				state.setInt(2, list1.get(1));
				state.setInt(3, list1.get(2));
				state.setInt(4, list1.get(3));
				state.setInt(5, list1.get(4));
				state.setInt(6, list1.get(5));
				state.executeUpdate();		
				state.close();
				
				sc.close();
			}
			System.out.println("已完成寫入資料庫");
		}catch (Exception e) {
			System.out.println("輸入錯誤,請重新輸入");
			number();
		}		
	}

	@Override
	public ArrayList<Dao> findall() throws SQLException {
		String sqlstr = "SELECT * FROM number";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		ResultSet rs = state.executeQuery();
		System.out.println("您的樂透號碼");
		ArrayList<Dao> list = new ArrayList<Dao>();
		while(rs.next()) {
			// System.out.println("第"+rs.getInt(1)+"組 "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getInt(6)+" "+rs.getInt(7));
			Dao dao = new Dao();
			dao.setId(rs.getInt(1));
			dao.setNumber1(rs.getInt(2));
			dao.setNumber2(rs.getInt(3));
			dao.setNumber3(rs.getInt(4));
			dao.setNumber4(rs.getInt(5));
			dao.setNumber5(rs.getInt(6));
			dao.setNumber6(rs.getInt(7));
			list.add(dao);			
		}
		rs.close();
		state.close();
		return list;
	}

	@Override
	public void createConn() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 需更改成使用者MySQL的user&password
			String urlStr = "jdbc:mysql://localhost:3306/jdbc?user=user&password=password&serverTimezone=UTC";
			conn = DriverManager.getConnection(urlStr);
			System.out.println("Connection Open Status:"+!conn.isClosed());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void closeConn() throws SQLException {
		if(conn!=null) {
		conn.close();
		System.out.println("Connection Closed");
		}
		
	}

}
