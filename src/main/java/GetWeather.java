import java.io.IOException;
import java.util.*;


import com.jayway.jsonpath.JsonPath;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.parser.ParseException;


public class GetWeather {


    public void getWeatherFromAPi() throws IOException, ParseException {

        Map<String, List<String>> cityMap = new GetCitiesFromJson().getCitiesFromLocal();
        Set<String> keys = cityMap.keySet();
        List<Map> finalWeatherList=new ArrayList<>();
        for (String key:keys) {
            String cityString;
            cityString = String.join(",", cityMap.get(key));
            String url = "http://api.openweathermap.org/data/2.5/group?" +
                    "id=" + cityString + "&units=metric&" +
                    "appid=6d4c202370ae8d7c76ee8edefc512a4f";

            HttpClient client = new HttpClient();
            GetMethod method = new GetMethod(url);
            client.executeMethod(method);
            String responseString = method.getResponseBodyAsString();
            finalWeatherList.addAll(JsonPath.read(responseString, "list"));
        }

        Map<String,List<String>> weatherMap=new HashMap<>();

        Iterator<Map> iterator = finalWeatherList.stream().iterator();
        while (iterator.hasNext()){
            String city= String.valueOf(iterator.next().get("name"));
            List<Map> weather= (List<Map>) iterator.next().get("weather");
            String weatherMain= String.valueOf(weather.get(0).get("main"));
            if (weatherMap.containsKey(weatherMain)){
                weatherMap.get(weatherMain).add(city);
            }
            else{
                List<String> cityList=new ArrayList<>();
                cityList.add(city);
                weatherMap.put(weatherMain, cityList);
            }
        }
            for (String mapKey:weatherMap.keySet()){
                System.out.println(mapKey+"--"+weatherMap.get(mapKey));
            }

        }


}
