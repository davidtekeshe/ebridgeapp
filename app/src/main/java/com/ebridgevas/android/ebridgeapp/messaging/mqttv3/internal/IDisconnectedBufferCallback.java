/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution. 
 *
 * The Eclipse Public License is available at 
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 *   http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *    James Sutton - Initial Contribution for Automatic Reconnect & Offline Buffering
 */
package com.ebridgevas.android.ebridgeapp.messaging.mqttv3.internal;

import com.ebridgevas.android.ebridgeapp.messaging.mqttv3.BufferedMessage;
import com.ebridgevas.android.ebridgeapp.messaging.mqttv3.MqttException;

public interface IDisconnectedBufferCallback {
	
	public void publishBufferedMessage(BufferedMessage bufferedMessage) throws MqttException;

}
