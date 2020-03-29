
package org.peanuts.voice.model.msnlp.req;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Document {

    @SerializedName("id")
    private String mId;
    @SerializedName("language")
    private String mLanguage;
    @SerializedName("text")
    private String mText;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

}
