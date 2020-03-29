
package org.peanuts.voice.model.nlu;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Intent {

    @SerializedName("confidence")
    private Double mConfidence;
    @SerializedName("name")
    private Object mName;

    public Double getConfidence() {
        return mConfidence;
    }

    public void setConfidence(Double confidence) {
        mConfidence = confidence;
    }

    public Object getName() {
        return mName;
    }

    public void setName(Object name) {
        mName = name;
    }

}
