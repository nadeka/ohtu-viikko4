package ohtu;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String studentNr = "012345678";

        if (args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2016.herokuapp.com/students/"+studentNr+"/submissions";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String bodyText = null;

        try {
            response = client.execute(httpGet);
            entity = response.getEntity();
            bodyText = IOUtils.toString(entity.getContent());
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        int totalDoneExercises = 0;
        int totalHours = 0;

        for (int i = 0; i < subs.length; i++) {
            System.out.println("Viikko " + (i + 1)
                    + ": tehtyjä tehtäviä yhteensä " + subs[i].getNumberOfDoneExercises()
                    + ", aikaa kului yhteensä " + subs[i].getHours() + " tuntia, tehdyt tehtävät: "
                    + subs[i].getDoneExercises());

            totalDoneExercises += subs[i].getNumberOfDoneExercises();
            totalHours += subs[i].getHours();
        }

        System.out.println("Yhteensä: " + totalDoneExercises + " tehtävää, " + totalHours + " tuntia");
    }
}
