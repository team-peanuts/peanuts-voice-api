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
package org.peanuts.voice.texttospeech;

import org.peanuts.voice.cart.ShoppingCartInfo;
import org.peanuts.voice.cart.ShoppingCartItem;
import org.peanuts.voice.strings.Strings;

import java.util.List;

public class ConfirmationTextGenerator {

  public String generateProductConfirmation(List<ShoppingCartItem> items) {
    String base = Strings.PRODUCTS_ADDED;
    for(ShoppingCartItem item : items) {
      base = base +" " +item.getItemQuantity() + " " + item.getItemName() +", ";
    }
    return base;
  }

  public String generateDeliveryAnnouncement(ShoppingCartInfo info) {
    String base = "Hi, I have good news: " + info.getFriendlyNeighbourName() + " will deliver the" +
            " " +
            "following products shortly: ";
    base = base + addShoppingInfo(info.getShoppingCartItems());
    return base + " Goodbye!";
  }

  private String addShoppingInfo(List<ShoppingCartItem> items) {
    String productInfo = "";
    for(ShoppingCartItem item : items) {
      productInfo = productInfo +" " +item.getItemQuantity() + " " + item.getItemName() +", ";
    }
    return productInfo;
  }
}
