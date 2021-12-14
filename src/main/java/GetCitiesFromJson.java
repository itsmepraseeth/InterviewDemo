import net.minidev.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCitiesFromJson {

    public Map<String, List<String>> getCitiesFromLocal() throws IOException, ParseException {

        JSONParser parser=new JSONParser();
        JSONArray jsonArray=(JSONArray) parser.parse(new FileReader("/Users/praseeths/IdeaProjects/InterviewDemo/src/test/resources/city-ids.json"));

        Map<String,List<String>> countryCityMap=new HashMap<>();
        for (Object object:jsonArray){
            String country=((Map)object).get("country").toString();
            String cityId= String.valueOf(((Map)object).get("id"));
            if (countryCityMap.containsKey(country)){
                if (countryCityMap.get(country).size()==10)
                    continue;
                countryCityMap.get(country).add(cityId);
            }
            else {
                List<String> cityList=new ArrayList<>();
                cityList.add(cityId);
                countryCityMap.put(country, cityList);
            }
        }

        return countryCityMap;
    }


}
