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
import prerequisites.Database;

public class Excel {
    private static prerequisites.Database db;
    
    
    public void pdf() throws IOException{
      //Loading an existing document
      
      PDDocument document = new PDDocument();
      PDPage my_page = new PDPage();
      document.addPage(my_page);
      document.save("test.pdf");
      
      File file = new File("test.pdf");
      //Retrieving the pages of the document 
      PDDocument doc =  document.load(file);
      PDPage page = doc.getPage(0);
      PDPageContentStream contentStream = new PDPageContentStream(doc, page);
      
      contentStream.beginText(); 
      contentStream.setLeading(14.5f);
      contentStream.setFont(PDType1Font.TIMES_BOLD, 24);
      contentStream.newLineAtOffset(25, 700);
      String text = "Invoice";
      contentStream.showText(text);
      contentStream.newLine();
      contentStream.newLine();
      contentStream.setFont(PDType1Font.TIMES_ROMAN,12);
      contentStream.showText("Dealer Name - " + "Pratik Gahane");
      contentStream.newLine();
      contentStream.setFont(PDType1Font.TIMES_ROMAN,8);
      contentStream.showText("pratik19gahane@gmail.com");
      contentStream.endText();
      contentStream.close();
      System.out.println("Content added");

      //Closing the content stream
      

      //Saving the document
      doc.save(new File("new_pdf.pdf"));

      //Closing the document
      doc.close();
    }
    
    public void distributoreReport(String path){
        Connection conn = null;
        Statement statement = null;
        ResultSet rst = null;
        
        try {
            conn = DriverManager.getConnection(db.dbString);
            statement = conn.createStatement();
            
            String sql = "Select * from distributor";
            
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
              studentData.put(Integer.toString(count), new Object[] { rst.getString("Id"), rst.getString("Name"), rst.getString("Contact_Number"),rst.getString("Email_Id"),rst.getString("Address"),rst.getString("City"),rst.getString("RegisteredAt") }); 
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
            conn = DriverManager.getConnection(db.dbString);
            statement = conn.createStatement();
            
            String sql = "Select * from product";
            
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
              studentData.put(Integer.toString(count), new Object[] { rst.getString("Distributor_Id"),rst.getString("Distributor_Name"), rst.getString("Name"), rst.getString("Price_per_Quantity"),rst.getString("Description") }); 
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
            
            
            
            FileOutputStream out = new FileOutputStream(
            new File("product_report.xlsx"));
            workbook.write(out);
            out.close();
            
        } catch (Exception e) {
        }
    }
 
    
 
}
