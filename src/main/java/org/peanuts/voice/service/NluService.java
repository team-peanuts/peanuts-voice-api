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
import org.peanuts.voice.cart.ShoppingCartItem;
import org.peanuts.voice.model.nlu.NluResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

public class NluService extends AbstractNlpService {

  private static final String NLU_ENDPOINT_URL = System.getenv("NLU_ENDPOINT_URL");

  public NluService(String text) {
    super(text);
  }

  @Override
  public List<ShoppingCartItem> extractShoppingCartItems() throws IOException {
    String response = Request.Get(makeEndpointAddress(text)).execute().returnContent().asString();
    NluResponse nluResponse = this.gson.fromJson(response, NluResponse.class);
    return toShoppingCartItems(nluResponse);
  }

  private List<ShoppingCartItem> toShoppingCartItems(NluResponse nluResponse) {
    return nluResponse
            .getEntities()
            .stream()
            .map(entity -> new ShoppingCartItem(entity.getValue(), 1))
            .collect(Collectors.toList());
  }

  private String makeEndpointAddress(String text) {
    return NLU_ENDPOINT_URL
            + "/parse?q="
            + URLEncoder.encode(text)
            + "&project=test";
  }
}
