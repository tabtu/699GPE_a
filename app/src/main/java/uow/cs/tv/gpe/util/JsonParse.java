package uow.cs.tv.gpe.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import uow.cs.tv.gpe.model.Activity;
import uow.cs.tv.gpe.model.City;
import uow.cs.tv.gpe.model.Club;
import uow.cs.tv.gpe.model.Msgs;
import uow.cs.tv.gpe.model.News;
import uow.cs.tv.gpe.model.Running;
import uow.cs.tv.gpe.model.RunningMan;
import uow.cs.tv.gpe.model.User;
import uow.cs.tv.gpe.model.VNews;
import uow.cs.tv.gpe.model.Venue;

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

    public List<Msgs> ParseJsonMsg (String str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Msgs>>(){}.getType();
        return gson.fromJson(str, type);
    }

    public Msgs ParseJsonSingleMsg (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<Msgs>(){}.getType();
        return gson.fromJson(str, type);
    }

    public List<Activity> ParseJsonActivity (String str) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Activity>>(){}.getType();
        return gson.fromJson(str, type);
    }

    public Running ParseJsonRunning (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<Running>(){}.getType();
        return gson.fromJson(str, type);
    }

    public RunningMan ParseJsonRunningMan (String  str) {
        Gson gson = new Gson();
        Type type = new TypeToken<RunningMan>(){}.getType();
        return gson.fromJson(str, type);
    }
}
