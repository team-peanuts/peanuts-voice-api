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
package org.peanuts.voice.speech;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1p1beta1.RecognitionAudio;
import com.google.cloud.speech.v1p1beta1.RecognitionConfig;
import com.google.cloud.speech.v1p1beta1.RecognizeResponse;
import com.google.cloud.speech.v1p1beta1.SpeechClient;
import com.google.cloud.speech.v1p1beta1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1p1beta1.SpeechRecognitionResult;
import com.google.cloud.speech.v1p1beta1.SpeechSettings;
import com.google.protobuf.ByteString;
import org.apache.http.client.fluent.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public enum TextToSpeech {

  INSTANCE;

  private final String GOOGLE_CREDENTIALS = System.getenv("GOOGLE_CREDENTIALS");

  private SpeechClient speechClient;
  private RecognitionConfig recognitionConfig;

  TextToSpeech() {
    try {
    ByteArrayInputStream credentialsStream = new ByteArrayInputStream(GOOGLE_CREDENTIALS.getBytes());
    GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
    FixedCredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);
    SpeechSettings speechSettings =
            SpeechSettings.newBuilder()
                    .setCredentialsProvider(credentialsProvider)
                    .build();

    this.speechClient = SpeechClient.create(speechSettings);
    this.recognitionConfig =
            RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setEnableWordConfidence(true)
                    .setUseEnhanced(true)
                    //.addSpeechContexts(speechContext)
                    .setLanguageCode("en-US")
                    .setModel("phone_call")
                    .build();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private byte[] getAudioFileContents(String audioFileUrl) throws IOException {
    return Request.Get(audioFileUrl).execute().returnContent().asBytes();
  }

  public String recognizeText(String audioFileUrl) throws IOException {
    ByteString audioBytes = ByteString.copyFrom(getAudioFileContents(audioFileUrl));
    RecognitionAudio audio = RecognitionAudio.newBuilder()
            .setContent(audioBytes).build();

    RecognizeResponse response = speechClient.recognize(recognitionConfig, audio);
    List<SpeechRecognitionResult> results = response.getResultsList();

    if (results.size() > 0) {
      SpeechRecognitionAlternative alternative = results.get(0).getAlternativesList().get(0);
      return alternative.getTranscript();
    } else {
      throw new IllegalArgumentException("Could not recognize");
    }
  }

}
