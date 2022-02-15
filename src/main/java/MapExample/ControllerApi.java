package MapExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class ControllerApi {

    Map<String, Double> currencyMap = new LinkedHashMap<>();
    private String dataToParse = null;
    public String importApiKey() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/java/MapExample/configController.txt"));
        String getapi = null;
        while (scanner.hasNextLine()) {
            getapi = scanner.nextLine();

        }
        return getapi;
    }




    public String downloadValueApi()  {

        try {
            final String apikey = importApiKey();
            final String url = "https://freecurrencyapi.net/api/v2/latest?apikey=" + apikey + "&base_currency=PLN";
            URL urlForGetRequest = new URL(url);
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                dataToParse = response.toString();
                in.close();

            } else {
                throw new Exception("Error in API Call");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataToParse;
    }


    public Map setValueFromApi() throws JsonProcessingException, FileNotFoundException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode temp = null;
        temp = mapper.readTree(downloadValueApi()).get("data");
        Iterator<String> iterator = temp.fieldNames();
        JsonNode finalTemp = temp;
        iterator.forEachRemaining(key -> {
            JsonNode worth = finalTemp.get(key);
            double tempWorth = Double.parseDouble(String.valueOf(worth));
            currencyMap.put(key, tempWorth);
        });

        return currencyMap;
    }

    public String convertedMoney(Double value, String key)
    {

        double conversionMoney;

        if (currencyMap.get(key) == null)
        {

            return "You enter wrong currency";

        }
        else
        {
            conversionMoney = value * currencyMap.get(key);
            conversionMoney *= 100;
            conversionMoney /= 100;
            return value + " in PLN is converted to " + conversionMoney +" " + key;

        }
    }

    @Override
    public String toString() {
        return String.valueOf(currencyMap);

    }




}
