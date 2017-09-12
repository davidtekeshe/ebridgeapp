package com.ebridgevas.android.ebridgeapp.messaging.mqtt;

import android.content.Context;
import android.util.Log;

import com.ebridgevas.android.ebridgeapp.messaging.mqttservice.MqttAndroidClient;
import com.ebridgevas.android.ebridgeapp.messaging.mqttv3.IMqttToken;
import com.ebridgevas.android.ebridgeapp.messaging.mqttv3.MqttConnectOptions;
import com.ebridgevas.android.ebridgeapp.messaging.mqttv3.MqttException;
import com.ebridgevas.android.ebridgeapp.messaging.mqttv3.MqttSecurityException;
import com.ebridgevas.android.ebridgeapp.messaging.mqttv3.MqttTopic;
import com.ebridgevas.android.ebridgeapp.messaging.mqtt.ActionListener.Action;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * TODO Class description
 */
public class ConnectionFactory {

    /**
     * {@link ChangeListener} for use with all {@link Connection} objects created by this instance of <code>ClientConnections</code>
     */
    private ChangeListener changeListener = new ChangeListener();

    public Connection connect(Context context) {

        // TODO read params from db


        boolean cleanSession = true;
        int keepAlive = 200;
        int timeout = 60;
        String username = null;
        String password = null;
        MqttTopic topic = null;
        String message = null;
        int qos = 0;
        boolean retained = false;

        String clientID = "27729745087";
        username = clientID;
        String server = "52.39.188.4";
        int port = 61613;

        boolean ssl = false;
        long id = System.currentTimeMillis();

        String uri = "tcp://" + server + ":" + port;
        Log.i("ConFactory", "uri : " + uri);

        MqttAndroidClient client = Connections.getInstance(context).createClient(context, uri, clientID);

        String clientHandle = uri + clientID;
        Log.i("ConFactory", "clientHandle : " + clientHandle);

//        MqttConnectOptions opts = new MqttConnectOptions();
//        opts.setCleanSession(cleanSession);
//        opts.setKeepAliveInterval(keepAlive);
//        opts.setConnectionTimeout(timeout);
//
//        opts.setPassword(password != null ? password.toCharArray() : null);
//        opts.setUserName(username);
//
//        if (topic != null) {
//            opts.setWill(topic, message.getBytes(), qos, retained);
//        }

        //now create the connection object
        Log.i("ConFactory", "clientID : " + clientID + ", host : " + server);
        Connection connection = new Connection(clientHandle, clientID, server, port, context, client, ssl);
//        connection.addConnectionOptions(opts);
//        connection.assignPersistenceId(id);

        connection.registerChangeListener(changeListener);

        String[] actionArgs = new String[1];
        actionArgs[0] = clientID;
        connection.changeConnectionStatus(Connection.ConnectionStatus.CONNECTING);

        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(cleanSession);
        conOpt.setConnectionTimeout(timeout);
        conOpt.setKeepAliveInterval(keepAlive);

        conOpt.setUserName(username);

        final ActionListener callback = new ActionListener(context, Action.CONNECT, clientHandle, actionArgs);

        boolean doConnect = true;
        client.setCallback(new MqttCallbackHandler(context, clientHandle));

        // set traceCallback
        client.setTraceCallback(new MqttTraceCallback());

        connection.addConnectionOptions(conOpt);
        Connections.getInstance(context).addConnection(connection);

        try {
            Log.i("ConFactor", "Connecting ...");
            client.connect(conOpt, null, callback);

//            Log.i("ConFactor", "Token : " + token.getResponse().toString());
//            Log.i("ConFactor", "Token payload : " + new String(token.getResponse().getPayload()));


        } catch (MqttException e) {
            Log.e("ConFactory", "MqttException : " + e);
        } catch(Exception e) {
            Log.e("ConFactory", "Exception : " + e);

        }
        return connection;
    }

//    /**
//     * Reconnect the selected client
//     */
//    public static void reconnect(Connection connection, Context context, String clientHandle) {
//
////        Connections.getInstance(context).getConnection(clientHandle).changeConnectionStatus(ConnectionStatus.CONNECTING);
//
////        Connection c = Connections.getInstance(context).getConnection(clientHandle);
//        try {
//            Log.i("ConFactory", "connecting ...");
//            connection.getClient()
//                    .connect(connection.getConnectionOptions(),
//                            null,
//                            new ActionListener(context, Action.CONNECT, clientHandle, null));
//            Log.i("ConFactory", "Done.");
//
//        }
//        catch (MqttSecurityException e) {
//            Log.e("ConFactory", "Failed to reconnect the client with the handle " + clientHandle, e);
//            connection.addAction("Client failed to connect");
//        }
//        catch (MqttException e) {
//            Log.e("ConFactory", "Failed to reconnect the client with the handle " + clientHandle, e);
//            connection.addAction("Client failed to connect");
//        }
//
//    }


    /**
     * This class ensures that the user interface is updated as the Connection objects change their states
     *
     *
     */
    private class ChangeListener implements PropertyChangeListener {

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent event) {

            Log.i("ConFactory", "ChangeListener.propertyChange : event " + event);
            if (!event.getPropertyName().equals(ActivityConstants.ConnectionStatusProperty)) {
                return;
            }
            /*
            clientConnections.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    clientConnections.arrayAdapter.notifyDataSetChanged();
                }

            });
            */
        }

    }
}
