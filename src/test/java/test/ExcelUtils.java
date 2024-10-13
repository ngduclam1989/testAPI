package test;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtils(String excelPath, String sheetName) throws IOException {
        try {
            FileInputStream file = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static JSONObject getCellData(int rowNum) throws IOException {
        DataFormatter formatter = new DataFormatter();
        JSONObject jsonObject = new JSONObject();

        for (int colNum = 0; colNum < sheet.getRow(rowNum).getPhysicalNumberOfCells(); colNum++) {
            String cellValue = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
            jsonObject.put("col" + colNum, cellValue);
        }

        return jsonObject;
    }

    public static int getRowCount() {
        int rowCount = sheet.getPhysicalNumberOfRows();
        System.out.println("Row count: " + rowCount);
        return rowCount;
    }

    public static List<JSONObject> getData(String excelPath, String sheetName) throws IOException {
        ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
        int rowCount = excel.getRowCount();
        List<JSONObject> data = new ArrayList<>();

        for (int i = 1; i < rowCount; i++) {
            data.add(getCellData(i));
        }

        return data;
    }
}

