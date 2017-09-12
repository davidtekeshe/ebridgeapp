package com.ebridgevas.android.ebridgeapp.mqtt.interfaces;

import com.ebridgevas.android.ebridgeapp.mqtt.impl.MqttException;

public interface IMqttMessage
{
	public int getQoS();
	public byte[] getPayload() throws MqttException;
	public boolean isRetained();	
	public boolean isDuplicate();
}
