package DemoJdbcDao;

import java.util.ArrayList;

public class DemoJdbcDaoAction {

	public static void main(String[] args) throws Exception {
		DaoJdbcImpl jdbcImpl = new DaoJdbcImpl();
		
		jdbcImpl.createConn();
		
		jdbcImpl.loginCheck();
		
		jdbcImpl.truncate();
		
		jdbcImpl.number();
				
		ArrayList<Dao> dao = jdbcImpl.findall();
		for(Dao m: dao){
			System.out.println("第"+m.getId()+"組"+" "+m.getNumber1()+" "+m.getNumber2()+" "+m.getNumber3()+" "+m.getNumber4()+" "+m.getNumber5()+" "+m.getNumber6());
		}
		
		jdbcImpl.closeConn();
	}

}
