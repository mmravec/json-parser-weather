import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by P3502441 on 17. 4. 2016.
 */
public class ExampleJson {

    static final String URL_OpenWeatherMap_weather_Kosice_ke =
            "http://api.openweathermap.org/data/2.5/forecast/city?id=724443&APPID=e4d26d20b0bd7b335bbf970717993ecd";


    public static void main(String[] args) {

        String result = "";

        try {
            URL url_weather = new URL(URL_OpenWeatherMap_weather_Kosice_ke);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url_weather.openConnection();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStreamReader inputStreamReader =
                        new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader =
                        new BufferedReader(inputStreamReader, 8192);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();

                String weatherResult = ParseResult(result);

                System.out.println(weatherResult);

            } else {
                System.out.println("Error in httpURLConnection.getResponseCode()!!!");
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(ExampleJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExampleJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ExampleJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static private String ParseResult(String json) throws JSONException {
        String parsedResult = "";
        //double celzius = 5.0 / 9.0;

        JSONObject jsonObject = new JSONObject(json);
        JSONObject city = jsonObject.getJSONObject("city");


        String result_weather = null;
        JSONArray JSONArray_list = jsonObject.getJSONArray("list");
        if (JSONArray_list.length() > 0) {
            JSONObject JSONObject_list = JSONArray_list.getJSONObject(0);

            int result_city_id = city.getInt("id");
            int result_id = JSONObject_list.getInt("dt");
            String result_date = JSONObject_list.getString("dt_txt");

            //cord
            double reusult_lon = city.getJSONObject("coord").getDouble("lon");
            double result_lat = city.getJSONObject("coord").getDouble("lat");

            //name
            String result_name = city.getString("country");
            double result_population = city.getDouble("population");

            //weather
            double result_temp = (JSONObject_list.getJSONObject("main").getDouble("temp") - 273.15);
            double result_humidity = JSONObject_list.getJSONObject("main").getDouble("humidity");
            double result_pressure = JSONObject_list.getJSONObject("main").getDouble("pressure");
            double result_temp_max = (JSONObject_list.getJSONObject("main").getDouble("temp_max") - 273.15);
            double result_temp_min = (JSONObject_list.getJSONObject("main").getDouble("temp_min") - 273.15);

            //clouds
            double result_clauds = JSONObject_list.getJSONObject("clouds").getDouble("all");

            //wind
            double result_wind_speed = JSONObject_list.getJSONObject("wind").getDouble("speed");
            double result_wind_deg = JSONObject_list.getJSONObject("wind").getDouble("deg");

            //rain
            double result_rain_per_tree_hour = JSONObject_list.getJSONObject("rain").getDouble("3h");

            result_weather = "Mesto: " + city.getString("name") + "\t Koordinaty: [" + reusult_lon + " " + result_lat + " ]\t Populacia:\t " + result_population + " \n" +
                    "Aktualna teplota: " + String.format("%.2f",result_temp) + " C \tVlhkost: " + result_humidity + "%\t Max/Min teplota: " + String.format("%.2f",result_temp_max) + "/" + String.format("%.2f",result_temp_min) + " C" +
                    "\nTlak: " + result_pressure + " hPa\t Rychlost vetra: " + result_wind_speed + " m/s\t Mnozstvo zrazok zaposledne 3h " + result_rain_per_tree_hour + " mil" +
                    "\nposledny update: " + result_date;


//            if (result_clauds >= 75 && result_clauds <= 100) {
//                System.out.println("Oblacno");
//            } else if (result_clauds < 75 && result_clauds > 35) {
//                System.out.println("Polooblacno");
//            } else {
//                System.out.println("Slnecno");
//            }
        }

            //System.out.println("Mesto:" + city.getString("name") + " populacia: " + city.getDouble("population") + " skratka: " + city.getString("country") );
            //System.out.print("temp:" + list.getDouble("list.temp"));
            return result_weather;

        }
    }




//        //"weather"
//        String result_weather;
//        JSONArray JSONArray_weather = jsonObject.getJSONArray("list");
//        if(JSONArray_weather.length() > 0){
//            JSONObject JSONObject_weather = JSONArray_weather.getJSONObject(0);
//            int result_id = JSONObject_weather.getInt("dt");
//            String result_main = JSONObject_weather.getString("main");
//            String result_description = JSONObject_weather.getString("description");
//            String result_icon = JSONObject_weather.getString("icon");
//
//            result_weather = "weather\tid: " + result_id +"\tmain: " + result_main + "\tdescription: " + result_description + "\ticon: " + result_icon;
//        }else{
//            result_weather = "weather empty!";
//        }
//
//        //"base"
//        String result_base = jsonObject.getString("base");
//


//        return
//                        "main\ttemp: " + result_temp + "\thumidity: " + result_humidity + "\tpressure: " + result_pressure + "\ttemp_min: " + result_temp_min + "\ttemp_max: " + result_temp_min + "\n" +
//                        result_wind + "\n" +
//                        "clouds\tall: " + result_all + "\n" +
//                        "dt: " + result_dt + "\n" +
//                        "id: " + result_id + "\n" +
//                        "name: " + result_name + "\n" +
//                        "cod: " + result_cod + "\n" +
//                        "\n";
//    }
//}
