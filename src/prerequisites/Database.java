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
import org.sqlite.SQLiteConnection;

public class Database {
	public static void main(String[] args) throws SQLException {
                Connection conn = null;
                Statement statement = null;
                Constant constant = new Constant();
		try {
                    conn = DriverManager.getConnection(constant.DB_STRING);
                    statement = conn.createStatement();
                    String distributorSql = "create table if not exists " + constant.DISTRIBUTOR_TABLE_NAME + " ("+ constant.DISTRIBUTOR_ID + " integer PRIMARY KEY AUTOINCREMENT," + constant.DISTRIBUTOR_NAME + " text," + constant.DISTRIBUTOR_CONTACT_NUMBER + " text," + constant.DISTRIBUTOR_EMAIL_ID + " text," + constant.DISTRIBUTOR_ADDRESS + " text," + constant.DISTRIBUTOR_CITY + " text," + constant.DISTRIBUTOR_REGISTERED_AT +" text)";
                    String productSql = "create table if not exists " + constant.PRODUCT_TABLE_NAME + "("+ constant.PRODUCT_NAME + " text," + constant.PRODUCT_PRICE + " text," + constant.PRODUCT_DESCRIPTION + " text," + constant.PRODUCT_AVAILABILITY + " text," + constant.PRODUCT_DISTRIBUTOR_ID + " text," + constant.PRODUCT_DISTRIBUTOR_NAME +" text)";
                    String billingSql = "create table if not exists " + constant.BILLING_TABLE_NAME + "("+ constant.BILLING_PO_INVOICE + " integer PRIMARY KEY AUTOINCREMENT," + constant.BILLING_DISTRIBUTOR_NAME + " text," + constant.BILLING_PRODUCT + " text," + constant.BILLING_QUANTITY + " text," + constant.BILLING_BALANCE + " text," + constant.BILLING_ADDRESS + " text," + constant.BILLING_CREATED_ON + " text," + constant.BILLING_DISTRIBUTOR_ID + " text)";
                    String transactionSql = "create table if not exists " + constant.TRANSACTION_TABLE_NAME + "("+ constant.TRANSACTION_GRN_NO + " integer PRIMARY KEY AUTOINCREMENT," + constant.TRANSACTION_DAMAGED_PRODUCT + " text," + constant.TRANSACTION_DEDUCTED_COST + " text," + constant.TRANSACTION_TOTAL_AMOUNT + " text," + constant.TRANSACTION_CREATED_ON + " text," + constant.TRANSACTION_PO_INVOICE + " text)";
                    
                    statement.execute(distributorSql);
                    System.out.println("Created Distributor Table");
                    
                    statement.execute(productSql);
                    System.out.println("Created Product Table");
                    
                    statement.execute(billingSql);
                    System.out.println("Created Billing Table");
                    
                    statement.execute(transactionSql);
                    System.out.println("Created Transaction Table");
                } catch (SQLException e) {
			System.out.println("Connection Error\n " + e.getMessage());
                        e.printStackTrace();
		}finally{
                    conn.close();
                    statement.close();
                }
		
	}
}
