import javax.jms.*;

import com.ibm.disthub2.client.Message;
import com.ibm.mq.MQQueue;
import com.ibm.mq.jms.MQQueueConnection;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQQueueReceiver;
import com.ibm.mq.jms.MQQueueSession;

public class MqStub {

    public static void main(String[] args) {
        try {
            MQQueueConnection mqConn;
            MQQueueConnectionFactory mqCF;

            final MQQueueSession mqQSession;

            MQQueue mqIN;
            MQQueueReceiver mqReceiver;

            mqCF = new MQQueueConnectionFactory();
            mqCF.setHostName("localhost");

            mqCF.setPort(1414);

            mqCF.setQueueManager("ADMIN");
            mqCF.setChannel("SYSTEM.DEF.SVRCONN");

            mqConn = (MQQueueConnection) mqCF.createConnection();
            mqQSession = (MQQueueSession) mqConn.createSession(true , Session.AUTO_ACKNOWLEDGE);

            mqIN = (MQQueue) mqQSession.createQueue("Mq.IN"); //входная

            mqReceiver = (MQQueueReceiver) mqQSession.createReceiver ((Queue) mqIN);

            javax.jms.MessageListener Listener = new javax.jms.MessageListener() {
                @Override
                public void onMessage(javax.jms.Message msg) {
                    System.out.println("Got message!");
                    if (msg instanceof TextMessage)
                    {
                        try {
                            TextMessage tMsg = (TextMessage) msg;
                            String msgText = tMsg.getText();
                            System.out.println(msgText);

                        } catch (JMSException e ) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            mqReceiver.setMessageListener (Listener);
            mqConn.start();
            System.out.println("Stub started.");
        }catch (JMSException e ) {
            e.printStackTrace();
        }try {
            Thread.sleep(60000);

        } catch (InterruptedException e  ) {
            e.printStackTrace();
        }
    }
}