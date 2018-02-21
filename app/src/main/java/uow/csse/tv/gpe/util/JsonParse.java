package uow.csse.tv.gpe.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import uow.csse.tv.gpe.model.City;
import uow.csse.tv.gpe.model.Club;
import uow.csse.tv.gpe.model.News;
import uow.csse.tv.gpe.model.User;
import uow.csse.tv.gpe.model.VNews;
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
        return gson.fromJson(str, type);
    }

    public List<Venue> ParseJsonVenue (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Venue>>(){}.getType();
        return gson.fromJson(str, type);
    }

    public List<Club> ParseJsonClub (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Club>>(){}.getType();
        return gson.fromJson(str, type);
    }

    public List<User> ParseJsonUsers (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>(){}.getType();
        return gson.fromJson(str, type);
    }

    public User ParseJsonUser (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<User>(){}.getType();
        return gson.fromJson(str, type);
    }

    public List<VNews> ParseJsonVNews (String str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<VNews>>(){}.getType();
        return gson.fromJson(str, type);
    }

    public List<City> ParseJsonCity (String str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<City>>(){}.getType();
        return gson.fromJson(str, type);
    }
}
