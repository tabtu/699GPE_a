package uow.csse.tv.gpe.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.model.News;
import uow.csse.tv.gpe.model.School;
import uow.csse.tv.gpe.model.Venue;

/**
 * Created by Vian on 2/6/2018.
 */

public class JsonParse {

    private String RequestStream;

    public String getRequestStream() {
        return RequestStream;
    }

    public void setRequestStream(String requestStream) {
        RequestStream = requestStream;
    }

    public JsonParse() {

    }

    public JsonParse(String str) {
        this.RequestStream = str;
    }

    public List<News> ParseJsonNews (String str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<News>>(){}.getType();
        List<News> result = gson.fromJson(str, type);
        return result;
    }

    public List<Venue> ParseJsonVenue (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Venue>>(){}.getType();
        List<Venue> result = gson.fromJson(str, type);
        return result;
    }

    public List<Club> ParseJsonClub (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Club>>(){}.getType();
        List<Club> result = gson.fromJson(str, type);
        return result;
    }

    public List<School> ParseJsonSchool (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<School>>(){}.getType();
        List<School> result = gson.fromJson(str, type);
        return result;
    }
}
