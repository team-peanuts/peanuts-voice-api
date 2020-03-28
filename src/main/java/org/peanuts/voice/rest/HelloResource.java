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
package org.peanuts.voice.rest;

import static org.peanuts.voice.dialog.DialogItemBuilder.*;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Record;
import com.twilio.twiml.voice.Say;
import org.peanuts.voice.cart.ShoppingCart;
import org.peanuts.voice.strings.Strings;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloResource extends AbstractResource {


  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response sayHelloWeb() {
    return ok("Hello World");
  }

  @POST
  @Produces(MediaType.APPLICATION_XML)
  public Response sayWelcomeText() {
    ShoppingCart.INSTANCE.initiateTransaction(callSid);
    Say say  = say(Strings.WELCOME);
    Record record = record("/products");
    VoiceResponse voiceResponse = voiceResponse(say, record);

    return ok(voiceResponse.toXml());
  }
}
