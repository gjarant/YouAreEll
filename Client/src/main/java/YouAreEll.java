
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

public class YouAreEll {

    YouAreEll() {
    }

    public static void main(String[] args) throws JsonProcessingException {
        YouAreEll urlhandler = new YouAreEll();
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
        urlhandler.postID("G", "Github");
        urlhandler.postMessage("gjarant", "bell7692", "Is it working?");
    }

    public void postID(String name, String github) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ID idTest = new ID( name, github);
        String payload = objectMapper.writeValueAsString(idTest);
        MakeURLCall("/ids", "POST", payload);
    }

    public void postMessage(String fromId, String toid, String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Messages messagesTest = new Messages(fromId, toid, message);
        String payload = objectMapper.writeValueAsString(messagesTest);
        String url = "/ids/" + toid + "/messages";
        MakeURLCall(url, "POST", payload);
    }


    public String get_ids() {
        return MakeURLCall("/ids", "GET", "");
    }

    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        String completeURL = "http://zipcode.rocks:8085" + mainurl;
        GetRequest request = Unirest.get(completeURL);

        if (method.equals("GET")) {
            try {
                return request.asJson().getBody().toString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("POST")){
            try {
                return Unirest.post(completeURL).body(jpayload).asJson().getBody().toString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
//        else if (method.equals("PUT")){
//            try {
//                return Unirest.put(completeURL).body(jpayload).asJson().getBody().toString();
//            } catch (UnirestException e) {
//                e.printStackTrace();
//            }
//        }
        return "nada";
    }
}
