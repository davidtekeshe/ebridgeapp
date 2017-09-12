package com.ebridgevas.android.ebridgeapp.mqtt.impl.paho;

import com.ebridgevas.android.ebridgeapp.mqtt.impl.MqttException;
import com.ebridgevas.android.ebridgeapp.mqtt.interfaces.IMqttClient;
import com.ebridgevas.android.ebridgeapp.mqtt.interfaces.IMqttClientFactory;
import com.ebridgevas.android.ebridgeapp.mqtt.interfaces.IMqttPersistence;

public class PahoMqttClientFactory implements IMqttClientFactory
{	
	@Override
	public IMqttClient create(String host, int port, String clientId,
		IMqttPersistence persistence) throws MqttException
	{
		PahoMqttClientPersistence persistenceImpl = null;
		if(persistence != null){
			persistenceImpl = new PahoMqttClientPersistence(persistence);
		}
		
		// TODO Auto-generated method stub
		return new PahoMqttClientWrapper(
			"tcp://"+host+":"+port, clientId, persistenceImpl);
	}
}
