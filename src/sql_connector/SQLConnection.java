package sql_connector;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Iterator;

import oracle.jdbc.OracleTypes;

public class SQLConnection {

	public static void main(String args[]){  
		
		String[] tidArray = null;
		String[] msisdnArray = null;
        String[] oidArray = null;
        String[] lsaArray = null;
        String[] remark2Arry = null;

        String[] rc = null;
        String[] rm = null;
		try {
	        Connection con = null;
	        con = getConnection();
	        

	        CallableStatement stmt;
	        stmt = con.prepareCall("{CALL PKG_OSSGW.SC_Retry_query(?,?,?,?,?,?,?,?)}");
	        stmt.setString(1,"PORTIN" );
	        stmt.registerOutParameter(2, OracleTypes.ARRAY, "ARRAY_STR50");
            stmt.registerOutParameter(3, OracleTypes.ARRAY, "ARRAY_STR50");
            stmt.registerOutParameter(4, OracleTypes.ARRAY, "ARRAY_STR50");
            stmt.registerOutParameter(5, OracleTypes.ARRAY, "ARRAY_STR50");
            stmt.registerOutParameter(6, OracleTypes.ARRAY, "ARRAY_STR50");
            stmt.registerOutParameter(7, OracleTypes.ARRAY, "ARRAY_STR50");
            stmt.registerOutParameter(8, OracleTypes.ARRAY, "ARRAY_STR50");
	        ResultSet rs2 = stmt.executeQuery();

	        if(stmt.getArray(2) !=null) {
	        	System.out.println("DB value not null");
	        	tidArray =  (String[]) stmt.getArray(2).getArray();
		        //System.out.println(stmt.getArray(2).getArray());
		        System.out.println("size : "+tidArray.length);
		        
		        System.out.println("123 : "+stmt.getArray(2).getArray());
		        for (int i = 0; i < tidArray.length; i++) {
		        	System.out.println("999");
					System.out.println(tidArray[i]);
				}
	        }
	        
	        if(tidArray.length !=0) {
	        	System.out.println("TID Array is not null!!");
                    msisdnArray = (String[]) stmt.getArray(3).getArray();
                    oidArray = (String[]) stmt.getArray(4).getArray();
                    lsaArray = (String[]) stmt.getArray(5).getArray();
                    remark2Arry = (String[]) stmt.getArray(6).getArray();
                    rm = (String[]) stmt.getArray(7).getArray();
                    rc = (String[]) stmt.getArray(8).getArray();
	        }
	        
	        con.close();

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public static Connection getConnection() {
	    Connection con = null;
	    try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@10.164.208.254:5811:MNPGW1D","indnp","indnp");  
			  
			//step3 create the statement object  
			java.sql.Statement stmt=con.createStatement();  
			  
			System.out.println("Connection successful");
			//step4 execute query  
			
			//step5 close the connection object  
			  
			}catch(Exception e){ System.out.println(e);}  
	    return con;
			}  
	
}
