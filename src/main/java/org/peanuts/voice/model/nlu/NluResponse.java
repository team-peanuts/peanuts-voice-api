
package org.peanuts.voice.model.nlu;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NluResponse {

    @SerializedName("entities")
    private List<Entity> mEntities;
    @SerializedName("intent")
    private Intent mIntent;
    @SerializedName("intent_ranking")
    private List<Object> mIntentRanking;
    @SerializedName("model")
    private String mModel;
    @SerializedName("project")
    private String mProject;
    @SerializedName("text")
    private String mText;

    public List<Entity> getEntities() {
        return mEntities;
    }

    public void setEntities(List<Entity> entities) {
        mEntities = entities;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent intent) {
        mIntent = intent;
    }

    public List<Object> getIntentRanking() {
        return mIntentRanking;
    }

    public void setIntentRanking(List<Object> intentRanking) {
        mIntentRanking = intentRanking;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        mModel = model;
    }

    public String getProject() {
        return mProject;
    }

    public void setProject(String project) {
        mProject = project;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

}
