/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.peanuts.voice.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.peanuts.voice.cart.ShoppingCartItem;
import org.peanuts.voice.model.msnlp.MsNlpResponse;
import org.peanuts.voice.model.msnlp.ResponseDocument;
import org.peanuts.voice.model.msnlp.req.Document;
import org.peanuts.voice.model.msnlp.req.MsNlpRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MicrosoftNlpService extends AbstractNlpService {

  private static final String MICROSOFT_ER_ENDPOINT = "https://eastus.api.cognitive.microsoft" +
          ".com/text/analytics/v3.0-preview.1/entities/recognition/general";
  private static final String SUBSCRIPTION_KEY = System.getenv("MS_SUBSCRIPTION_KEY");

  public MicrosoftNlpService(String text) {
    super(text);
  }

  @Override
  public List<ShoppingCartItem> extractShoppingCartItems() throws IOException {
    String response = Request
            .Post(MICROSOFT_ER_ENDPOINT)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("Ocp-Apim-Subscription-Key", SUBSCRIPTION_KEY)
            .body(new StringEntity(makeRequestBody()))
            .execute()
            .returnContent()
            .asString();

    MsNlpResponse responseDoc = gson.fromJson(response, MsNlpResponse.class);

    return toShoppingCartItems(responseDoc);
  }

  private List<ShoppingCartItem> toShoppingCartItems(MsNlpResponse responseDoc) {
    if (responseDoc.getDocuments().size() > 0) {
      ResponseDocument doc = responseDoc.getDocuments().get(0);
      return doc
              .getEntities()
              .stream()
              .filter(e -> e.getType().equals("Product"))
              .map(e -> new ShoppingCartItem(e.getText(), 1))
              .collect(Collectors.toList());
    } else {
      return Collections.emptyList();
    }
  }

  private String makeRequestBody() {
    MsNlpRequest req = new MsNlpRequest();
    Document reqDoc = new Document();
    reqDoc.setId("1");
    reqDoc.setLanguage("en");
    reqDoc.setText(text);

    req.setDocuments(Collections.singletonList(reqDoc));
    return gson.toJson(req);
  }

  public static void main(String[] args) {
    try {
      List<ShoppingCartItem> items = new MicrosoftNlpService("orange juice toilet paper lettuce " +
              "salad ravioli " +
              "noodles cheese steak" +
              " shampoo").extractShoppingCartItems();

      items.forEach(item -> System.out.println(item.getItemName()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
