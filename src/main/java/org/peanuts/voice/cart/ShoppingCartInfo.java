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
import java.util.List;

public class ShoppingCartInfo {

  private ShoppingCartCustomer shoppingCartCustomer;
  private List<ShoppingCartItem> shoppingCartItems;

  private long orderDate;
  private long expectedDeliveryDate;

  private OrderStatus orderStatus;

  public ShoppingCartInfo() {
    this.shoppingCartItems = new ArrayList<>();
    this.shoppingCartCustomer = new ShoppingCartCustomer();
  }

  public ShoppingCartCustomer getShoppingCartCustomer() {
    return shoppingCartCustomer;
  }

  public void setShoppingCartCustomer(ShoppingCartCustomer shoppingCartCustomer) {
    this.shoppingCartCustomer = shoppingCartCustomer;
  }

  public List<ShoppingCartItem> getShoppingCartItems() {
    return shoppingCartItems;
  }

  public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
    this.shoppingCartItems = shoppingCartItems;
  }

  public long getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(long orderDate) {
    this.orderDate = orderDate;
  }

  public long getExpectedDeliveryDate() {
    return expectedDeliveryDate;
  }

  public void setExpectedDeliveryDate(long expectedDeliveryDate) {
    this.expectedDeliveryDate = expectedDeliveryDate;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public void addShoppingCartItem(ShoppingCartItem item) {
    this.shoppingCartItems.add(item);
  }
}
