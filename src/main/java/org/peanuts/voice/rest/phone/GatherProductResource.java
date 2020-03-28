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
package org.peanuts.voice.rest.phone;

import static org.peanuts.voice.dialog.DialogItemBuilder.*;

import com.twilio.twiml.voice.Record;
import com.twilio.twiml.voice.Say;
import org.peanuts.voice.cart.ShoppingCart;
import org.peanuts.voice.cart.ShoppingCartItem;
import org.peanuts.voice.rest.AbstractResource;
import org.peanuts.voice.strings.Strings;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public class GatherProductResource extends AbstractResource {

  @POST
  @Produces(MediaType.APPLICATION_XML)
  public Response addProduct() {
    ShoppingCartItem item = new ShoppingCartItem("Klopapier", 1);
    ShoppingCart.INSTANCE.addProductToCart(callSid, item);
    System.out.println(callSid);
    System.out.println(recordingUrl);
    String product = "Ich habe 1 Rolle Klopapier hinzugefügt.";

    Say say = say(product + Strings.ADD_MORE_PRODUCTS);
    Record record = record("/add-more-products");
    return ok(voiceResponse(say, record).toXml());
  }
}
