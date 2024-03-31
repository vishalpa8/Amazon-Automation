package dataproviders;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class YamlDataReader {

    Yaml yaml = null;
    InputStream inputStream = null;
    Map<String,Object> object = null;

    public YamlDataReader(String fileName){
        yaml = new Yaml();
        inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        object = yaml.load(inputStream) ;
    }

    public ArrayList<Map<String,Object>> getList(String token){
        return getList(this.object,token);
    }

    private ArrayList<Map<String,Object>> getList(Map<String,Object>object,String token){
        String[] st = token.split("\\.");
        return (ArrayList<Map<String,Object>>)parseMap(object,token).get(st[st.length-1]);
    }

    private static Map<String, Object> parseMap(Map<String, Object> object, String token) {
        if(token.contains(".")){
            String[] st = token.split("\\.");
            object = parseMap((Map<String, Object>)object.get(st[0]),
                    token.replaceFirst(st[0]+".",""));
        }

        return object;
    }


}
