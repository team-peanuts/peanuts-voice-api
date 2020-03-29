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
package org.peanuts.voice.dialog;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Record;
import com.twilio.twiml.voice.Say;

public class DialogItemBuilder {

  public static Say say(String text) {
    return new Say.Builder(text).build();
  }

  public static Record record(String actionPath) {
    return new Record.Builder()
            .action(actionPath)
            .playBeep(true)
            .build();
  }

  public static VoiceResponse voiceResponse(Say say, Record record) {
   return new VoiceResponse.Builder()
            .say(say)
            .record(record)
            .build();
  }

  public static VoiceResponse voiceResponse(Say say) {
    return new VoiceResponse.Builder()
            .say(say)
            .build();
  }
}
