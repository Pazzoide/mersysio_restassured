package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelManager {
    public static ArrayList<ArrayList<String>> getData(String filePath, String sheetTitle, int columnCount) {
        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        Sheet sheet1 = null;
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            sheet1 = workbook.getSheet(sheetTitle);
        } catch (IOException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        for (int i = 0; i < sheet1.getPhysicalNumberOfRows(); i++) {
            ArrayList<String> satir = new ArrayList<>();
            for (int j = 0; j < columnCount; j++) {
                satir.add(sheet1.getRow(i).getCell(j).toString());
            }
            matrix.add(satir);
        }
        return matrix;
    }

    public static void excelWriter(String filePath, ITestResult iTestResult, String testStatus) {
        File file = new File(filePath);
        if (!file.exists()) {
            XSSFWorkbook excelWorkbook = new XSSFWorkbook();
            XSSFSheet excelSheet = excelWorkbook.createSheet("Page1");
            Row row = excelSheet.createRow(0);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(iTestResult.getName());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(testStatus);
            try {
                FileOutputStream outputWriter = new FileOutputStream(filePath);
                excelWorkbook.write(outputWriter);
                excelWorkbook.close();
                outputWriter.close();
            } catch (IOException Ex) {
                throw new RuntimeException(Ex);
            }
        } else {
            FileInputStream fileSource2 = null;
            Workbook excelWorkbook2 = null;
            Sheet excelSheet2 = null;
            try {
                fileSource2 = new FileInputStream(filePath);
                excelWorkbook2 = WorkbookFactory.create(fileSource2);
                excelSheet2 = excelWorkbook2.getSheetAt(0);
            } catch (IOException Ex) {
                throw new RuntimeException(Ex);
            }
            int lastRowIndex = excelSheet2.getPhysicalNumberOfRows();
            Row newRow = excelSheet2.createRow(lastRowIndex);
            Cell newCell1 = newRow.createCell(0);
            newCell1.setCellValue(iTestResult.getName());
            Cell newCell2 = newRow.createCell(1);
            newCell2.setCellValue(testStatus);
            try {
                fileSource2.close();
                FileOutputStream outputStream = new FileOutputStream(filePath);
                excelWorkbook2.write(outputStream);
                excelWorkbook2.close();
                outputStream.close();
            } catch (IOException Ex) {
                throw new RuntimeException(Ex);
            }
        }
    }
}
