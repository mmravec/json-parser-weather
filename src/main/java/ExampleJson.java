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

            String[] parts = result_date.split(" ",2);
            String part1 = parts[0];


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
//            double result_rain_per_tree_hour;
//            if (!JSONObject_list.has("rain")) {
//                result_rain_per_tree_hour = 0.0;
//            } else {
//                result_rain_per_tree_hour = JSONObject_list.getJSONObject("rain").getDouble("3h");
//            }

            //String result_weather = null;
            JSONArray JSONArray_list_next_day = jsonObject.getJSONArray("list");
            if (JSONArray_list.length() > 0) {
                JSONObject JSONObject_list_next_day = JSONArray_list_next_day.getJSONObject(8);

                //name
                String result_name_next_day = city.getString("country");
                double result_population_next_day = city.getDouble("population");

                String result_date_next_day = JSONObject_list_next_day.getString("dt_txt");

                //cord
                double reusult_lon_next_day = city.getJSONObject("coord").getDouble("lon");
                double result_lat_next_day = city.getJSONObject("coord").getDouble("lat");

                //weather
                double result_temp_next_day = (JSONObject_list_next_day.getJSONObject("main").getDouble("temp") - 273.15);
                double result_humidity_next_day = JSONObject_list_next_day.getJSONObject("main").getDouble("humidity");
                double result_pressure_next_day = JSONObject_list_next_day.getJSONObject("main").getDouble("pressure");
                double result_temp_max_next_day = (JSONObject_list_next_day.getJSONObject("main").getDouble("temp_max") - 273.15);
                double result_temp_min_next_day = (JSONObject_list_next_day.getJSONObject("main").getDouble("temp_min") - 273.15);

                //clouds
                double result_clauds_next_day = JSONObject_list.getJSONObject("clouds").getDouble("all");

                //wind
                double result_wind_speed_next_day = JSONObject_list_next_day.getJSONObject("wind").getDouble("speed");
                double result_wind_deg_next_day = JSONObject_list_next_day.getJSONObject("wind").getDouble("deg");

                //rain
//                double result_rain_per_tree_hour_next_day;
//                if (!JSONObject_list_next_day.isNull("rain")) {
//                    result_rain_per_tree_hour_next_day = 0.0;
//                } else {
//                    result_rain_per_tree_hour_next_day = JSONObject_list_next_day.getJSONObject("rain").getDouble("3h");
//                }






                //String result_weather = null;
                JSONArray JSONArray_list_night = jsonObject.getJSONArray("list");
                if (JSONArray_list_night.length() > 0) {
                    JSONObject JSONObject_list_night = JSONArray_list_night.getJSONObject(4);

                    double result_temp_max_night = (JSONObject_list_night.getJSONObject("main").getDouble("temp_max") - 273.15);
                    double result_temp_min_night = (JSONObject_list_night.getJSONObject("main").getDouble("temp_min") - 273.15);

                    double result_clauds_night = JSONObject_list_night.getJSONObject("clouds").getDouble("all");

                    String result_date_night = JSONObject_list_night.getString("dt_txt");


                    String result_night;
                    if (result_clauds_night >= 75 && result_clauds_night <= 100) {
                        result_night = ("Oblacno");
                    } else if (result_clauds_night < 75 && result_clauds_night > 35) {
                        result_night = ("Polooblacno");
                    } else {
                        result_night = ("Slnecno");
                    }
                    //System.err.print("Update: " + result_date_night +" Oblcnst: " + result_night + "\n Nocna teplota MAX/MIN: " + String.format("%.2f", result_temp_max_night) + " C/" + String.format("%.2f", result_temp_min_night) + " C " );

                    String result;
                    if (result_clauds >= 75 && result_clauds <= 100) {
                        result = ("Oblacno");
                    } else if (result_clauds < 75 && result_clauds > 35) {
                        result = ("Polooblacno");
                    } else {
                        result = ("Slnecno");
                    }

                    if (result_clauds_next_day >= 75 && result_clauds_next_day <= 100) {
                        result = ("Oblacno");
                    } else if (result_clauds_next_day < 75 && result_clauds_next_day > 35) {
                        result = ("Polooblacno");
                    } else {
                        result = ("Slnecno");
                    }

                    result_weather = "*************Predpoved na Dnes [" + part1 + "]************* \n\nMesto: " + city.getString("name") + "\t Koordinaty: [" + reusult_lon + " " + result_lat + "]\t Populacia:\t " + result_population + " \n" +
                            "Aktualna teplota: " + String.format("%.2f", result_temp) + " C \tVlhkost: " + result_humidity + "%\t Max/Min teplota: " + String.format("%.2f", result_temp_max) + "/" + String.format("%.2f", result_temp_min) + " C Obloha: " + result +
//                            "\nTlak: " + result_pressure + " hPa\t Rychlost vetra: " + result_wind_speed + " m/s\t Mnozstvo zrazok zaposledne 3h " + result_rain_per_tree_hour + " mil" +
                            "\nposledny update: " + result_date + "\n\n" +
                            "*************Predpoved na zajtra************* \n" +
                            "\nMesto:" + city.getString("name") + "t Koordinaty: [" + reusult_lon + " " + result_lat + "]\t Populacia:\t " + result_population + " \n" +
                            "Aktualna teplota: " + String.format("%.2f", result_temp_next_day) + " C \tVlhkost: " + result_humidity_next_day + "%\t Max/Min teplota: " + String.format("%.2f", result_temp_max_next_day) + "/" + String.format("%.2f", result_temp_min) + " C Obloha: " + result +
                            "\nTlak: " + result_pressure_next_day + " hPa\t Rychlost vetra: " + result_wind_speed_next_day + " m/s\t Mnozstvo zrazok zaposledne 3h " + //result_rain_per_tree_hour_next_day + " mil" +
                            "\nposledny update: " + result_date_next_day;
                }
            }
        }
            return result_weather;
        }
    }

