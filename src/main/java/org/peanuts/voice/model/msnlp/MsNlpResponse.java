
package org.peanuts.voice.model.msnlp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MsNlpResponse {

    @SerializedName("documents")
    private List<ResponseDocument> mDocuments;
    @SerializedName("errors")
    private List<Object> mErrors;
    @SerializedName("modelVersion")
    private String mModelVersion;

    public List<ResponseDocument> getDocuments() {
        return mDocuments;
    }

    public void setDocuments(List<ResponseDocument> documents) {
        mDocuments = documents;
    }

    public List<Object> getErrors() {
        return mErrors;
    }

    public void setErrors(List<Object> errors) {
        mErrors = errors;
    }

    public String getModelVersion() {
        return mModelVersion;
    }

    public void setModelVersion(String modelVersion) {
        mModelVersion = modelVersion;
    }

}
