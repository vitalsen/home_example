import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetRequest {
    static String sURL = "https://speller.yandex.net/services/spellservice";

    public static void main(String[] args) {

        CloseableHttpResponse resp1 = null;

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(sURL + "/checkText?text=синхрафазатрон+в+дубне");

            resp1 = httpClient.execute(httpGet);

            System.out.println(EntityUtils.toString(resp1.getEntity()));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                resp1.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

