
package org.peanuts.voice.model.msnlp;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Entity {

    @SerializedName("length")
    private Long mLength;
    @SerializedName("offset")
    private Long mOffset;
    @SerializedName("score")
    private Double mScore;
    @SerializedName("subtype")
    private String mSubtype;
    @SerializedName("text")
    private String mText;
    @SerializedName("type")
    private String mType;

    public Long getLength() {
        return mLength;
    }

    public void setLength(Long length) {
        mLength = length;
    }

    public Long getOffset() {
        return mOffset;
    }

    public void setOffset(Long offset) {
        mOffset = offset;
    }

    public Double getScore() {
        return mScore;
    }

    public void setScore(Double score) {
        mScore = score;
    }

    public String getSubtype() {
        return mSubtype;
    }

    public void setSubtype(String subtype) {
        mSubtype = subtype;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
