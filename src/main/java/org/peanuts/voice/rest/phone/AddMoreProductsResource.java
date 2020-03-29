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
import org.peanuts.voice.model.SimpleYesNoQuestion;
import org.peanuts.voice.rest.AbstractPostResource;
import org.peanuts.voice.service.MoreProductsRecognitionService;

import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/add-more-products")
public class AddMoreProductsResource extends AbstractPostResource {

  @POST
  @Produces(MediaType.APPLICATION_XML)
  public Response addMoreProductsAnswer() {
    try {
      SimpleYesNoQuestion answer = new MoreProductsRecognitionService(recordingUrl).recognize();
      if (answer == SimpleYesNoQuestion.YES) {
        Say say = say("What else do you want?");
        Record record = record("/products");
        return ok(voiceResponse(say, record).toXml());
      } else {
        Say say = say("Please tell me your name");
        Record record = record("/gather-name");
        return ok(voiceResponse(say, record).toXml());
      }
    } catch (IOException e) {
      e.printStackTrace();
      Say say = say("Sorry, something went wrong. Goodbye!");
      return ok(voiceResponse(say).toXml());
    }

  }
}
