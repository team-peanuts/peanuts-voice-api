
package org.peanuts.voice.model.msnlp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ResponseDocument {

    @SerializedName("entities")
    private List<Entity> mEntities;
    @SerializedName("id")
    private String mId;

    public List<Entity> getEntities() {
        return mEntities;
    }

    public void setEntities(List<Entity> entities) {
        mEntities = entities;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

}
