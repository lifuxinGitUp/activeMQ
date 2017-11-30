package com.lanou.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
/**
 * Created by dllo on 17/11/29.
 * 消息消费者服务
 */
@Service
public class ConsumerService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;
//万事可忘，难忘铭心一段；千般易淡，未淡刻骨一缘。


    public TextMessage receive(Destination destination) {

//        jmsTemplate.getDefaultDestinationName()
//        jmsTemplate.getDefaultDestination()
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        if (textMessage != null) {
            try {
                System.out.println("接收到的消息内容是: " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return textMessage;
    }

}
