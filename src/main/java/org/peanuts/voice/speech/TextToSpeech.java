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

public class TextToSpeech {

  private static final String AUDIO_FILE_URL = System.getenv("AUDIO_FILE_URL");
  private static final String GOOGLE_CREDENTIALS = System.getenv("GOOGLE_CREDENTIALS");

  private String audioFileUrl;

  public TextToSpeech(String audioFileUrl) {
    this.audioFileUrl = audioFileUrl;
  }

  public String recognizeTextGoogle() throws IOException {
    try {
      ByteArrayInputStream credentialsStream = new ByteArrayInputStream(GOOGLE_CREDENTIALS.getBytes());
      GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
      FixedCredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);
      SpeechSettings speechSettings =
              SpeechSettings.newBuilder()
                      .setCredentialsProvider(credentialsProvider)
                      .build();

      SpeechClient speechClient = SpeechClient.create(speechSettings);
      ByteString audioBytes = ByteString.copyFrom(getAudioFileContents());

      RecognitionConfig config =
              RecognitionConfig.newBuilder()
                      .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                      .setEnableWordConfidence(true)
                      .setUseEnhanced(true)
                      //.addSpeechContexts(speechContext)
                      .setLanguageCode("en-US")
                      .setModel("phone_call")
                      .build();
      RecognitionAudio audio = RecognitionAudio.newBuilder()
              .setContent(audioBytes).build();

      RecognizeResponse response = speechClient.recognize(config, audio);
      List<SpeechRecognitionResult> results = response.getResultsList();

      for (SpeechRecognitionResult result : results) {
        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
        return alternative.getTranscript();
      }
    } catch(Exception e) {
      e.printStackTrace();
    }

    return "";
  }

  private byte[] getAudioFileContents() throws IOException {
    return Request.Get(this.audioFileUrl).execute().returnContent().asBytes();
  }

}
