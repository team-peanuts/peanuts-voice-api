
package org.peanuts.voice.model.nlu;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Entity {

    @SerializedName("confidence")
    private Double mConfidence;
    @SerializedName("end")
    private Long mEnd;
    @SerializedName("entity")
    private String mEntity;
    @SerializedName("extractor")
    private String mExtractor;
    @SerializedName("start")
    private Long mStart;
    @SerializedName("value")
    private String mValue;

    public Double getConfidence() {
        return mConfidence;
    }

    public void setConfidence(Double confidence) {
        mConfidence = confidence;
    }

    public Long getEnd() {
        return mEnd;
    }

    public void setEnd(Long end) {
        mEnd = end;
    }

    public String getEntity() {
        return mEntity;
    }

    public void setEntity(String entity) {
        mEntity = entity;
    }

    public String getExtractor() {
        return mExtractor;
    }

    public void setExtractor(String extractor) {
        mExtractor = extractor;
    }

    public Long getStart() {
        return mStart;
    }

    public void setStart(Long start) {
        mStart = start;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

}
