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
package org.peanuts.voice.data;

import org.peanuts.voice.cart.OrderStatus;
import org.peanuts.voice.cart.ShoppingCartCustomer;
import org.peanuts.voice.cart.ShoppingCartInfo;
import org.peanuts.voice.cart.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

public class DummyDataProvider {

  public static ShoppingCartInfo makeDummyInfo(String callerId) {
    ShoppingCartInfo info = new ShoppingCartInfo();
    info.setShoppingCartCustomer(makeDummyCustomer(callerId));
    info.setOrderStatus(OrderStatus.ORDER_CREATED);
    info.setShoppingCartItems(makeDummyItems());

    return info;
  }

  private static ShoppingCartCustomer makeDummyCustomer(String callerId) {
    ShoppingCartCustomer customer = new ShoppingCartCustomer();
    customer.setCustomerAddress("Dummy Address, Zurich, Switzerland");
    customer.setCustomerName("Dummy name");
    customer.setCallerId("ABCCallerId");
    return customer;
  }

  private static List<ShoppingCartItem> makeDummyItems() {
    List<ShoppingCartItem> items = new ArrayList<>();
    items.add(new ShoppingCartItem("Chocolate", 1));
    items.add(new ShoppingCartItem("Toilet Paper", 1));

    return items;
  }
}
