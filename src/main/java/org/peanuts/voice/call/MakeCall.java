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
package org.peanuts.voice.call;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;
import org.peanuts.voice.cart.ShoppingCartInfo;
import org.peanuts.voice.texttospeech.ConfirmationTextGenerator;

public enum MakeCall {

  INSTANCE;

  private final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
  private final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
  private final String TWILIO_FROM_NUMBER = System.getenv("TWILIO_FROM_NUMBER");

  MakeCall() {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
  }

  public void makeCall(ShoppingCartInfo info) {
    Call call = Call.creator(new PhoneNumber(info.getShoppingCartCustomer().getCustomerPhoneNumber()),
            new PhoneNumber(TWILIO_FROM_NUMBER),
            new Twiml(new ConfirmationTextGenerator().generateDeliveryAnnouncement(info))).create();
    System.out.println(call.getSid());
  }

}
