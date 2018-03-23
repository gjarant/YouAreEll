
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import java.io.IOException;
import java.util.List;

public class YouAreEll {

    YouAreEll() {
    }

    public static void main(String[] args) throws IOException {
        YouAreEll urlhandler = new YouAreEll();
        //urlhandler.postID("G", "Github");
        //urlhandler.postMessage("gjarant", "bell7692", "Is it working?");
    }

    public String messageParse() throws IOException {
        String str = MakeURLCall("/messages", "GET", "");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Message> message = objectMapper.readValue(str, new TypeReference<List<Message>>(){});

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.size(); i++) {
            sb.append("Time: " +message.get(i).getTimestamp() + "\n");
            sb.append("From: " +message.get(i).getFromid() + "\n");
            sb.append("To: " +message.get(i).getToid() + "\n");
            sb.append("Message: " +message.get(i).getMessage() + "\n\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String myMessageParse(String githubid) throws IOException {
        String id = "/ids/" + githubid + "/messages";
        String str = MakeURLCall(id, "GET", "");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Message> message = objectMapper.readValue(str, new TypeReference<List<Message>>(){});

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.size(); i++) {
            sb.append("Time: " +message.get(i).getTimestamp() + "\n");
            sb.append("From: " +message.get(i).getFromid() + "\n");
            sb.append("To: " +message.get(i).getToid() + "\n");
            sb.append("Message: " +message.get(i).getMessage() + "\n\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String myMessageParseFromFriend(String githubid, String friendgithub) throws IOException {
        String id = "/ids/" + githubid + "/from/" + friendgithub;
        String str = MakeURLCall(id, "GET", "");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Message> message = objectMapper.readValue(str, new TypeReference<List<Message>>(){});

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.size(); i++) {
            sb.append("Time: " +message.get(i).getTimestamp() + "\n");
            sb.append("From: " +message.get(i).getFromid() + "\n");
            sb.append("To: " +message.get(i).getToid() + "\n");
            sb.append("Message: " +message.get(i).getMessage() + "\n\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String idParse() throws IOException {
        String str = MakeURLCall("/ids", "GET", "");
        ObjectMapper objectMapper = new ObjectMapper();
        List<ID> ids = objectMapper.readValue(str, new TypeReference<List<ID>>(){});

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            sb.append("User ID: " + ids.get(i).getUserid() + "\n");
            sb.append("Name: " + ids.get(i).getName() + "\n");
            sb.append("GitHub ID: " + ids.get(i).getGithub() + "\n\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public void postID(String name, String github) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ID idTest = new ID(name, github);
        String payload = objectMapper.writeValueAsString(idTest);
        MakeURLCall("/ids", "POST", payload);
    }

    public void postMessage(String fromId, String toid, String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Message messagesTest = new Message(fromId, toid, message);
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
        HttpRequestWithBody requestWithBody = Unirest.post(completeURL);

        if (method.equals("GET")) {
            try {
                return request.asJson().getBody().toString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

        }
        else if (method.equals("POST")){
            try {
                return requestWithBody.body(jpayload).asJson().getBody().toString();
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
