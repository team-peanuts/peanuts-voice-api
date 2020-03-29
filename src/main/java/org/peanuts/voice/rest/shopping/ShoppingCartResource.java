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
package org.peanuts.voice.rest.shopping;

import org.peanuts.voice.call.MakeCall;
import org.peanuts.voice.cart.OrderStatus;
import org.peanuts.voice.cart.ShoppingCart;
import org.peanuts.voice.cart.ShoppingCartInfo;
import org.peanuts.voice.data.DummyDataProvider;
import org.peanuts.voice.rest.AbstractResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("shopping-cart")
public class ShoppingCartResource extends AbstractResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllShoppingCarts() {
    return ok(ShoppingCart.INSTANCE.getAllShoppingCarts());
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response addShoppingCartInfo(ShoppingCartInfo info) {
    ShoppingCart.INSTANCE.addShoppingCartInfo(info);
    return ok();
  }

  @GET
  @Path("/{callerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getShoppingCartInfoForCaller(@PathParam("callerId") String callerId) {
    return ok(ShoppingCart.INSTANCE.getShoppingCartInfoForCaller(callerId));
  }

  @POST
  @Path("/dummy/{callerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addDummyInfo(@PathParam("callerId") String callerId) {
    ShoppingCart.INSTANCE.addShoppingCartInfo(DummyDataProvider.makeDummyInfo(callerId));
    return ok(ShoppingCart.INSTANCE.getShoppingCartInfoForCaller(callerId));
  }

  @PUT
  @Path("/{callerId}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateShoppingCartInfoForCaller(@PathParam("callerId") String callerId,
                                                  ShoppingCartInfo info) {
    ShoppingCart.INSTANCE.addShoppingCartInfo(info);
    if (info.getShoppingCartCustomer().getCustomerPhoneNumber() != null) {
      if (info.getOrderStatus() == OrderStatus.ORDER_WILL_BE_DELIVERED_SOON) {
        MakeCall.INSTANCE.makeCall(info);
      }
    }
    return ok(info);
  }

  @DELETE
  @Path("/{callerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteShoppingCartInfoForCaller(@PathParam("callerId") String callerId) {
    ShoppingCart.INSTANCE.deleteShoppingCartInfo(callerId);
    return ok();
  }
}
