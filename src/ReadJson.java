import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// ShazamAPI --> top tracks in the Country
//

public class ReadJson {
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", 1704310046);
        file.put("Tuition Fees", 65400);


        // To print in JSON format.
        System.out.print(file.get("Tuition Fees"));
        ReadJson readingIsWhat = new ReadJson();

    }

    public ReadJson(){
        try {
            pull();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public  void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://shazam-api6.p.rapidapi.com/shazam/top_tracks_country?country_code=US&limit=10");
            //link curled to Java using curlconverter.com

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept", "application/json");

            conn.setRequestProperty("x-rapidapi-host", "shazam-api6.p.rapidapi.com");
            conn.setRequestProperty("x-rapidapi-key", "38668c27cemshdc8714e89254ec4p19d5d2jsn3c04734edc82");


            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            JSONObject result = (JSONObject) jsonObject.get("result"); //getting inside of the result Object
            System.out.println(result);

            org.json.simple.JSONArray data = (org.json.simple.JSONArray) result.get("data"); //grabs the whole data array
            System.out.println(data);

            int n =   data.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test =(JSONObject) data.get(i);
                System.out.println(test);
                //grabs one of the songs from data array

                JSONObject attributes = (JSONObject) test.get("attributes");
                System.out.println(attributes);


                String name = (String) attributes.get("name");
                System.out.println(name);
                //grabs String from attributes



            }

            //String height= (String)jsonObject.get("height");

        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }
}


