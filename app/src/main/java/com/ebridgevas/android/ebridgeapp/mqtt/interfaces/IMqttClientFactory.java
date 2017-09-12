package com.ebridgevas.android.ebridgeapp.mqtt.interfaces;

import com.ebridgevas.android.ebridgeapp.mqtt.impl.MqttException;

public interface IMqttClientFactory
{
	public IMqttClient create(String host, int port, String clientId, IMqttPersistence persistence) throws MqttException;
}
