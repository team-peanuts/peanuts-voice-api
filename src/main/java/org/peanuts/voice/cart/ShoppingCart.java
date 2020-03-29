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
package org.peanuts.voice.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ShoppingCart {

  INSTANCE;

  private Map<String, ShoppingCartInfo> currentlyOpenTransactions;

  ShoppingCart() {
    this.currentlyOpenTransactions = new HashMap<>();
  }

  public void initiateTransaction(String callerId) {
    ShoppingCartInfo shoppingCartInfo = new ShoppingCartInfo();
    shoppingCartInfo.setOrderStatus(OrderStatus.ORDER_INITIATED);
    this.currentlyOpenTransactions.put(callerId, shoppingCartInfo);
  }

  public void addProductToCart(String callerId, ShoppingCartItem item) {
    if (exists(callerId)) {
      this.currentlyOpenTransactions.get(callerId).addShoppingCartItem(item);
    }
  }

  public void addCustomerName(String callerId, String customerName) {
    if (exists(callerId)) {
      this.currentlyOpenTransactions.get(callerId).getShoppingCartCustomer().setCustomerName(customerName);
    }
  }

  public void addAddress(String callerId, String customerAddress) {
    if (exists(callerId)) {
      this.currentlyOpenTransactions.get(callerId).getShoppingCartCustomer().setCustomerAddress(customerAddress);
    }
  }

  public void confirmOrder(String callerId) {
    if (exists(callerId)) {
      this.currentlyOpenTransactions.get(callerId).setOrderStatus(OrderStatus.ORDER_CREATED);
    }
  }

  private Boolean exists(String callerId) {
    return currentlyOpenTransactions.containsKey(callerId);
  }

  public List<ShoppingCartInfo> getAllShoppingCarts() {
    return new ArrayList<>(this.currentlyOpenTransactions.values());
  }

  public void addShoppingCartInfo(ShoppingCartInfo info) {
    this.currentlyOpenTransactions.put(info.getShoppingCartCustomer().getCallerId(), info);
  }

  public void deleteShoppingCartInfo(String callerId) {
    this.currentlyOpenTransactions.remove(callerId);
  }

  public ShoppingCartInfo getShoppingCartInfoForCaller(String callerId) {
    return this.currentlyOpenTransactions.get(callerId);
  }
}
