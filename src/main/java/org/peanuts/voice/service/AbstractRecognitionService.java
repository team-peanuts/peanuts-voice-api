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

import org.peanuts.voice.speech.TextToSpeech;

import java.io.IOException;

public abstract class AbstractRecognitionService<T> {

  protected String audioFileUrl;

  public AbstractRecognitionService(String audioFileUrl) {
    this.audioFileUrl = audioFileUrl;
  }

  protected String recognizeText() throws IOException {
    return TextToSpeech.INSTANCE.recognizeText(this.audioFileUrl);
  }

  public abstract T recognize() throws IOException;
}
