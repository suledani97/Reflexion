package hu.bme.aut.reflexion.data;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ResultData extends RealmObject {
    private String name;

    private RealmList<Integer>  times;

    public ResultData(){
        times = new RealmList<Integer>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void addTimeToCollection(Integer time){
        times.add(time);
    }
}
