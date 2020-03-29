
package org.peanuts.voice.model.msnlp.req;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MsNlpRequest {

    @SerializedName("documents")
    private List<Document> mDocuments;

    public List<Document> getDocuments() {
        return mDocuments;
    }

    public void setDocuments(List<Document> documents) {
        mDocuments = documents;
    }

}
