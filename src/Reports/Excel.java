package Reports;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import prerequisites.Constant;

public class Excel {
    private static prerequisites.Constant constant;

    
    public void distributoreReport(String path){
        Connection conn = null;
        Statement statement = null;
        ResultSet rst = null;
        
        try {
            conn = DriverManager.getConnection(constant.DB_STRING);
            statement = conn.createStatement();
            
            String sql = "Select * from " + constant.DISTRIBUTOR_TABLE_NAME;
            System.out.println(sql);
            
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet(" Distributor Report ");
            XSSFRow row;
            
            spreadsheet.setColumnWidth(0, 1000);
            spreadsheet.setColumnWidth(1, 5000);
            spreadsheet.setColumnWidth(2, 6000);
            spreadsheet.setColumnWidth(3, 7000);
            spreadsheet.setColumnWidth(4, 8000);
            spreadsheet.setColumnWidth(5, 5000);
            spreadsheet.setColumnWidth(6, 6000);
            
            
            Map<String, Object[]> studentData  = new TreeMap<String, Object[]>();
            studentData.put("1", new Object[] { "Id", "Name", "Contact Number","Email Id","Address","City","Registered On" });
            
            rst = statement.executeQuery(sql);
            
            int count = 2;
            while(rst.next()){
              studentData.put(Integer.toString(count), new Object[] { rst.getString(constant.DISTRIBUTOR_ID), rst.getString(constant.DISTRIBUTOR_NAME), rst.getString(constant.DISTRIBUTOR_CONTACT_NUMBER),rst.getString(constant.DISTRIBUTOR_EMAIL_ID),rst.getString(constant.DISTRIBUTOR_ADDRESS),rst.getString(constant.DISTRIBUTOR_CITY),rst.getString(constant.DISTRIBUTOR_REGISTERED_AT) }); 
              count++;
            }
            
            
            
            Set<String> keyid = studentData.keySet();
            int rowid = 0;
            for (String key : keyid) {
  
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = studentData.get(key);
            int cellid = 0;
  
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }
            
            
            File file = new File(path + "/distributor_report.xlsx");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            
        } catch (Exception e) {
        }
    }
    
    
    public void productReport(String path){
        Connection conn = null;
        Statement statement = null;
        ResultSet rst = null;
        
        try {
            conn = DriverManager.getConnection(constant.DB_STRING);
            statement = conn.createStatement();
            
            String sql = "Select * from " + constant.PRODUCT_TABLE_NAME;
            
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet(" Product Report ");
            XSSFRow row;
            
            spreadsheet.setColumnWidth(0, 5000);
            spreadsheet.setColumnWidth(1, 5000);
            spreadsheet.setColumnWidth(2, 6000);
            spreadsheet.setColumnWidth(3, 7000);
            spreadsheet.setColumnWidth(4, 8000);
            spreadsheet.setColumnWidth(5, 5000);
            spreadsheet.setColumnWidth(6, 6000);
            
            
            Map<String, Object[]> studentData  = new TreeMap<String, Object[]>();
            studentData.put("1", new Object[] { "Distributor Id","Distributor Name", "Product Name", "Price per Quantity","Description" });
            
            rst = statement.executeQuery(sql);
            
            int count = 2;
            while(rst.next()){
              studentData.put(Integer.toString(count), new Object[] { rst.getString(constant.PRODUCT_DISTRIBUTOR_ID),rst.getString(constant.PRODUCT_DISTRIBUTOR_NAME), rst.getString(constant.PRODUCT_NAME), rst.getString(constant.PRODUCT_NAME),rst.getString(constant.PRODUCT_DESCRIPTION) }); 
              count++;
            }
            
            
            
            Set<String> keyid = studentData.keySet();
            int rowid = 0;
            for (String key : keyid) {
  
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = studentData.get(key);
            int cellid = 0;
  
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }
            
            
            
            File file = new File(path + "/product_report.xlsx");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            
        } catch (Exception e) {
        }
    }
 
    
 
}
