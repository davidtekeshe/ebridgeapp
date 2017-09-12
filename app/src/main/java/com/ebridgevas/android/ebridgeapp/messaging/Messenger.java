package com.ebridgevas.android.ebridgeapp.messaging;

public class Messenger {

    public static class Builder {

        public Builder baseUrl(String s) {
            return this;
        }

        public Builder addConverterFactory(GsonConverterFactory factory){
            return this;
        }

        public Messenger build() {
            return new Messenger();
        }
    }
}
