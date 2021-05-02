/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prerequisites;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
	public static final String dbString = "jdbc:sqlite:test.db";
	public static final String distributoTableName = "distributor";
        public static final String productTableName = "product";
	public static String distributorCount;
        
        //column names
        public static final String Id = "Id";
        public static final String Name = "Name";
        public static final String ContactNo = "Contact_Number";
        public static final String EmailId = "Email_Id";
        public static final String Address = "Address";
        public static final String City = "City";
        public static final String RegisteredAt = "RegisteredAt";
        
        //database column names for distributor
        public static final String column0_Id = Id + " integer PRIMARY KEY AUTOINCREMENT,";
        public static final String column1_Name = Name +" text,";
        public static final String column2_ContactNo = ContactNo +" text,";
        public static final String column3_EmailId = EmailId +" text,";
        public static final String column4_Address = Address+" text,";
        public static final String column5_City = City+" text,";
        public static final String column6_RegisteredAt = RegisteredAt +" text";
        
        //database column names for product list
        public static final String productName = "Name text,";
        public static final String productPricePerQuantity = "Price_per_Quantity integer,";
        public static final String productDescription = "Description text,";
        public static final String distributrId = "Distributor_Id integer,";
        public static final String distributorName = "Distributor_Name text";
        
	public static void main(String[] args) throws SQLException {
                Connection conn = null;
                Statement statement = null;
            
		try {
                    conn = DriverManager.getConnection(dbString);
                    statement = conn.createStatement();
                    String distributorSql = "create table if not exists " + distributoTableName + " ("+column0_Id + column1_Name +column2_ContactNo + column3_EmailId + column4_Address + column5_City + column6_RegisteredAt +")";
                    String productSql = "create table if not exists " + productTableName + "("+productName+productPricePerQuantity+productDescription+distributrId+distributorName+")";
                    String pmlSql = "create table if not exists pml (PO_Invoice integer PRIMARY KEY AUTOINCREMENT, Distributor_Name text,Product text,Quantity text, Balance text, Address text, Created_On text)";
                    String grnSql = "create table if not exists grn (GRN_No integer PRIMARY KEY AUTOINCREMENT, Damaged_Product text, Deducted_Cost text, Total_Amount text, Date text,PO_Invoice integer)";
                            
                    statement.execute(distributorSql);
                    System.out.println("Created Distributor Table");
                    
                    statement.execute(productSql);
                    System.out.println("Created Product Table");
                    
                    statement.execute(pmlSql);
                    System.out.println("Created pml Table");
                    
                    statement.execute(grnSql);
                    System.out.println("Created grn Table");
                } catch (SQLException e) {
			System.out.println("Connection Error\n " + e.getMessage());
		}finally{
                    conn.close();
                    statement.close();
                }
		
	}
        
}
