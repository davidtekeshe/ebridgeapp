package com.ebridgevas.android.ebridgeapp.services;

import com.ebridgevas.android.ebridgeapp.messaging.GsonConverterFactory;
import com.ebridgevas.android.ebridgeapp.messaging.Messenger;

public class MessagingService {

   public Messenger buildEBridgeMessanger(){

       return new Messenger.Builder()
               .baseUrl("http://ebridgevas.com")
               .addConverterFactory(GsonConverterFactory.create())
               .build();
   }
}
