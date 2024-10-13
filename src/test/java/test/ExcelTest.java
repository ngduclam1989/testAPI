package test;

import java.io.IOException;

public class ExcelTest {

    public ExcelTest(String excelPath, String sheetName) throws IOException {

        ExcelUtils excel = new ExcelUtils(excelPath,sheetName);
        int CountRow = excel.getRowCount();

        for (int i = 1; i < CountRow; i++) {
            for (int j = 0; j < 4; j++) {
//                excel.getCellData(i,j);

            }
            System.out.println();

        }
//        excel.getCellData(2,0);

    }
}
