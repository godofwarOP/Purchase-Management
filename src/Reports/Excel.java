package Reports;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class Excel {

    private static prerequisites.Constant constant;

    public void distributoreReport(String path) {
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

            Map<String, Object[]> studentData = new TreeMap<String, Object[]>();
            studentData.put("1", new Object[]{"Id", "Name", "Contact Number", "Email Id", "Address", "City", "Registered On"});

            rst = statement.executeQuery(sql);

            int count = 2;
            while (rst.next()) {
                studentData.put(Integer.toString(count), new Object[]{rst.getString(constant.DISTRIBUTOR_ID), rst.getString(constant.DISTRIBUTOR_NAME), rst.getString(constant.DISTRIBUTOR_CONTACT_NUMBER), rst.getString(constant.DISTRIBUTOR_EMAIL_ID), rst.getString(constant.DISTRIBUTOR_ADDRESS), rst.getString(constant.DISTRIBUTOR_CITY), rst.getString(constant.DISTRIBUTOR_REGISTERED_AT)});
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
                    cell.setCellValue((String) obj);
                }
            }

            File file = new File(path + "/distributor_report.xlsx");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();

        } catch (Exception e) {
        }
    }

    public void productReport(String path) {
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

            Map<String, Object[]> studentData = new TreeMap<String, Object[]>();
            studentData.put("1", new Object[]{"Distributor Id", "Distributor Name", "Product Name", "Price per Quantity", "Description"});

            rst = statement.executeQuery(sql);

            int count = 2;
            while (rst.next()) {
                studentData.put(Integer.toString(count), new Object[]{rst.getString(constant.PRODUCT_DISTRIBUTOR_ID), rst.getString(constant.PRODUCT_DISTRIBUTOR_NAME), rst.getString(constant.PRODUCT_NAME), rst.getString(constant.PRODUCT_NAME), rst.getString(constant.PRODUCT_DESCRIPTION)});
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
                    cell.setCellValue((String) obj);
                }
            }

            File file = new File(path + "/product_report.xlsx");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();

        } catch (Exception e) {
        }
    }

    public void transactionReport(String path) {
        Connection conn = null;
        Statement statement = null;
        ResultSet rst = null;
        ResultSet rst1 = null;
        Statement statement1 = null;

        try {
            conn = DriverManager.getConnection(constant.DB_STRING);
            statement = conn.createStatement();

            String sql = "Select * from " + constant.TRANSACTION_TABLE_NAME;

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet(" Transaction Report ");
            XSSFRow row;

            spreadsheet.setColumnWidth(0, 1000);
            spreadsheet.setColumnWidth(1, 5000);
            spreadsheet.setColumnWidth(2, 6000);
            spreadsheet.setColumnWidth(3, 7000);
            spreadsheet.setColumnWidth(4, 8000);
            spreadsheet.setColumnWidth(5, 5000);
            spreadsheet.setColumnWidth(6, 6000);

            Map<String, Object[]> studentData = new TreeMap<String, Object[]>();
            studentData.put("1", new Object[]{"GRN No", "PO Invoice", "Distributor Name", "Product Name", "Shipping Address", "Damaged Product", "Total Amount", "GRN Created On"});

            rst = statement.executeQuery(sql);
            statement1 = conn.createStatement();
            int count = 2;
            while (rst.next()) {
                String grnNo = rst.getString(constant.TRANSACTION_GRN_NO);
                String totalAmount = rst.getString(constant.TRANSACTION_TOTAL_AMOUNT);
                String date = rst.getString(constant.TRANSACTION_CREATED_ON);
                String invoiceNo = rst.getString(constant.TRANSACTION_PO_INVOICE);
                String damagedProduct = rst.getString(constant.TRANSACTION_DAMAGED_PRODUCT);

                String sql1 = "select * from " + constant.BILLING_TABLE_NAME + " where " + constant.BILLING_PO_INVOICE + " = " + invoiceNo;
                rst1 = statement1.executeQuery(sql1);

                while (rst1.next()) {
                    studentData.put(Integer.toString(count), new Object[]{grnNo, invoiceNo, rst1.getString(constant.BILLING_DISTRIBUTOR_NAME), rst1.getString(constant.BILLING_PRODUCT), rst1.getString(constant.BILLING_ADDRESS), damagedProduct, totalAmount, date});
                    count++;
                }
            }

            Set<String> keyid = studentData.keySet();
            int rowid = 0;
            for (String key : keyid) {

                row = spreadsheet.createRow(rowid++);
                Object[] objectArr = studentData.get(key);
                int cellid = 0;

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    cell.setCellValue((String) obj);
                }
            }

            File file = new File(path + "/transaction_report.xlsx");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();

        } catch (Exception e) {
        }
    }

}
