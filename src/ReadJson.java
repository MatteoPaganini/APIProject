import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;



// ShazamAPI --> top tracks in the Country

public class ReadJson {

    private JFrame mainFrame;
    private JPanel controlPanel;

    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private JTextArea textArea6;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;


    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", 1704310046);
        file.put("Tuition Fees", 65400);


        // To print in JSON format.
        System.out.print(file.get("Tuition Fees"));
        ReadJson app = new ReadJson();

    }

    public ReadJson(){
        setupLayout();
        try {
            pull();
            pullBoston();
            pullMiami();
            pullLA();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //figure out how to implement a layout when
    public void setupLayout (){
        mainFrame = new JFrame("Shazam API Project Layout");
        mainFrame.setSize(900, 900);
        mainFrame.setLayout(new GridLayout(3, 3));

        textArea1 = new JTextArea("Most Popular Songs in the US:" + "\n");
        textArea2 = new JTextArea("Most Popular Songs in Boston:" + "\n");
        textArea3 = new JTextArea("Most Popular Songs in Miami:" + "\n");
        textArea4 = new JTextArea("TA4");
        textArea5 = new JTextArea("TA5");
        textArea6 = new JTextArea("TA6");
        scrollPane1 = new JScrollPane(textArea1);
        scrollPane2 = new JScrollPane(textArea2);
        scrollPane3 = new JScrollPane(textArea3);



        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        mainFrame.add(scrollPane1);
        mainFrame.add(scrollPane2);
        mainFrame.add(scrollPane3);
        mainFrame.add(textArea4);
        mainFrame.add(textArea5);
        mainFrame.add(textArea6);

        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout(2, 2)); //setting controlPanel as a BorderLayout

        mainFrame.setVisible(true);
    }



    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";

        try {

            URL url = new URL("https://shazam-api6.p.rapidapi.com/shazam/top_tracks_country?country_code=US&limit=10");
            //link curled to Java using curlconverter.com
            //URL is a unique address that points to a specific resource on the internet (within the server)

            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //http is a set of rules that govern how web browsers and servers communicate
            conn.setRequestMethod("GET"); //httpURLConnection is a specific class that requests information from the URL through the HTTP

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
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            JSONObject result = (JSONObject) jsonObject.get("result"); //getting inside the result Object
            //System.out.println(result);

            org.json.simple.JSONArray data = (org.json.simple.JSONArray) result.get("data"); //grabs the whole data array
            //System.out.println(data);

            int n =   data.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test =(JSONObject) data.get(i);
                //System.out.println(test);
                //grabs one of the songs from data array
                System.out.println(i+1);
                textArea1.append((String.valueOf(i+1)) + "\n");

                JSONObject attributes = (JSONObject) test.get("attributes");
                //System.out.println(attributes);

                String name = (String) attributes.get("name");
                System.out.println(name);
                textArea1.append(name + "\n");
                //grabs String from attributes

                String artistName = (String) attributes.get("artistName");
                System.out.println(artistName);
                textArea1.append(artistName + "\n" + "\n");

            }


        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }

    //second pull method for "Best Songs in City"

    public void pullBoston() throws ParseException {
        String output = "abc";
        String totlaJson="";

        try {

            URL url = new URL("https://shazam-api6.p.rapidapi.com/shazam/top_tracks_city?city_name=Boston&country_code=US&limit=10");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

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
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            JSONObject result = (JSONObject) jsonObject.get("result"); //getting inside the result Object

            org.json.simple.JSONArray data = (org.json.simple.JSONArray) result.get("data"); //grabs the whole data array


            int n =   data.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test =(JSONObject) data.get(i);
                System.out.println(i+1);
                textArea2.append((String.valueOf(i+1)) + "\n");

                JSONObject attributes = (JSONObject) test.get("attributes");

                String name = (String) attributes.get("name");
                System.out.println(name);
                textArea2.append(name + "\n");
                //grabs String from attributes

                String artistName = (String) attributes.get("artistName");
                System.out.println(artistName);
                textArea2.append(artistName + "\n" + "\n");

            }


        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }

    public void pullMiami() throws ParseException {
        String output = "abc";
        String totlaJson="";

        try {

            URL url = new URL("https://shazam-api6.p.rapidapi.com/shazam/top_tracks_city?city_name=Miami&country_code=US&limit=10");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

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
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            JSONObject result = (JSONObject) jsonObject.get("result"); //getting inside the result Object

            org.json.simple.JSONArray data = (org.json.simple.JSONArray) result.get("data"); //grabs the whole data array


            int n =   data.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test =(JSONObject) data.get(i);
                System.out.println(i+1);
                textArea3.append((String.valueOf(i+1)) + "\n");

                JSONObject attributes = (JSONObject) test.get("attributes");

                String name = (String) attributes.get("name");
                System.out.println(name);
                textArea3.append(name + "\n");
                //grabs String from attributes

                String artistName = (String) attributes.get("artistName");
                System.out.println(artistName);
                textArea3.append(artistName + "\n" + "\n");

            }


        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }

    public void pullLA() throws ParseException {
        String output = "abc";
        String totlaJson="";

        try {

            URL url = new URL("https://shazam-api6.p.rapidapi.com/shazam/top_tracks_city?city_name=Los%20Angeles&country_code=US&limit=10");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

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
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            JSONObject result = (JSONObject) jsonObject.get("result"); //getting inside the result Object

            org.json.simple.JSONArray data = (org.json.simple.JSONArray) result.get("data"); //grabs the whole data array


            int n =   data.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test =(JSONObject) data.get(i);
                System.out.println(i+1);
                textArea4.append((String.valueOf(i+1)) + "\n");

                JSONObject attributes = (JSONObject) test.get("attributes");

                String name = (String) attributes.get("name");
                System.out.println(name);
                textArea4.append(name + "\n");
                //grabs String from attributes

                String artistName = (String) attributes.get("artistName");
                System.out.println(artistName);
                textArea4.append(artistName + "\n" + "\n");

            }


        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }


}


