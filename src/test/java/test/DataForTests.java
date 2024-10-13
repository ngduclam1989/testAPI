package test;

import org.testng.annotations.DataProvider;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class DataForTests {

    @DataProvider(name = "dataForTest")
    public Object[] [] dataForPost() throws IOException {

        String filePath = "src/data/data.xlsx";
        String sheetName = "test";
        List<JSONObject> data = ExcelUtils.getData(filePath, sheetName);

        Object[][] dataArray = new Object[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            JSONObject json = data.get(i);
            dataArray[i][0] = json.getString("col0");
            dataArray[i][1] = json.getString("col1");
        }

        return dataArray;
    }


    
    @DataProvider(name="DataForDelete")
    public Object[] dataForDelete(){
        return new Object[]{
                4,5,6,7
        };
    }
}
