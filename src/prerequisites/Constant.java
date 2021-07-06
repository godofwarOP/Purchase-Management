/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prerequisites;

/**
 *
 * @author godofwarop
 */
public class Constant {
    
    //Database connection string
    public static final String DB_STRING = "jdbc:sqlite:test.db";
    
    //Database Table Name
    public static final String DISTRIBUTOR_TABLE_NAME = "distributor";
    public static final String PRODUCT_TABLE_NAME = "product";
    public static final String BILLING_TABLE_NAME = "billing";
    public static final String TRANSACTION_TABLE_NAME = "transactionDetail";
    
    //Distributor Column Names
    public static final String DISTRIBUTOR_ID = "Id";
    public static final String DISTRIBUTOR_NAME = "Name";
    public static final String DISTRIBUTOR_CONTACT_NUMBER = "Contact_Number";
    public static final String DISTRIBUTOR_EMAIL_ID = "Email_Id";
    public static final String DISTRIBUTOR_ADDRESS = "Address";
    public static final String DISTRIBUTOR_CITY = "City";
    public static final String DISTRIBUTOR_REGISTERED_AT = "Registered_At";
            
    //Product Column Names
    public static final String PRODUCT_NAME = "Name";
    public static final String PRODUCT_PRICE = "Price";
    public static final String PRODUCT_DESCRIPTION = "Description";
    public static final String PRODUCT_AVAILABILITY = "Availability";
    public static final String PRODUCT_DISTRIBUTOR_ID = "Distributor_Id";
    public static final String PRODUCT_DISTRIBUTOR_NAME = "Distributor_Name";
    
    //Billing Column Names
    public static final String BILLING_PO_INVOICE = "PO_Invoice";
    public static final String BILLING_DISTRIBUTOR_NAME = "Distributor_Name";
    public static final String BILLING_PRODUCT = "Product";
    public static final String BILLING_QUANTITY = "Quantity";
    public static final String BILLING_BALANCE = "Balance";
    public static final String BILLING_ADDRESS = "Address";
    public static final String BILLING_CREATED_ON = "Created_On";
    public static final String BILLING_DISTRIBUTOR_ID = "Distributor_Id";
    
    //Transaction Column Names
    public static final String TRANSACTION_GRN_NO = "GRN_No";
    public static final String TRANSACTION_DAMAGED_PRODUCT = "Damaged_Product";
    public static final String TRANSACTION_DEDUCTED_COST = "Deducted_Cost";
    public static final String TRANSACTION_TOTAL_AMOUNT = "Total_Amount";
    public static final String TRANSACTION_CREATED_ON = "Date";
    public static final String TRANSACTION_PO_INVOICE = "PO_Invoice";
    public static final String TRANSACTION_BILLING_CREATED_ON = "Billing_Created_On";
    public static final String TRANSACTION_SHIPPING_ADDRESS = "Address";
}
