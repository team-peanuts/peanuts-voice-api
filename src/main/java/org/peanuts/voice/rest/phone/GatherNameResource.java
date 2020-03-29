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
import org.peanuts.voice.cart.ShoppingCartInfo;
import org.peanuts.voice.rest.AbstractPostResource;
import org.peanuts.voice.service.AddressRecognitionService;

import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("gather-name")
public class GatherNameResource extends AbstractPostResource {

  @POST
  @Produces(MediaType.APPLICATION_XML)
  public Response processNameAnswer() {
    try {
      String name = new AddressRecognitionService(recordingUrl).recognize();
      ShoppingCartInfo info = ShoppingCart.INSTANCE.getShoppingCartInfoForCaller(callSid);
      info.getShoppingCartCustomer().setCustomerName(name);
      ShoppingCart.INSTANCE.addShoppingCartInfo(info);
      Say say = say("Thanks " +name +"! Please tell me your address");
      Record record = record("/gather-address");
      return ok(voiceResponse(say, record).toXml());
    } catch (IOException e) {
      e.printStackTrace();
      Say say = say("Sorry, something went wrong. Goodbye!");
      return ok(voiceResponse(say).toXml());
    }
  }
}
