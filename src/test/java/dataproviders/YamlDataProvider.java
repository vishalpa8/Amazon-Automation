package dataproviders;

import org.testng.annotations.DataProvider;
import reusable.TestConfiguration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class YamlDataProvider {


    @DataProvider(name = "testData-dataSource")
    public static Object[][] getData(Method method){
        String testDataMode = "smoke";
        String className = method.getDeclaringClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1);

        System.out.println("In the Data: " + className + '.' + method.getName() + '.' + testDataMode);

        ArrayList<Map<String,Object>> list = TestConfiguration.yamlDataReader.getList
                (className + "." + method.getName() + "." + testDataMode);

        Object[][] data = new Object[list.size()][1];
        int row = 0;
        int col = 0;

        for (Map<String,Object> mapValue : list){
            data[row][col] = mapValue;
            row++;
        }


        return data;
    }

}