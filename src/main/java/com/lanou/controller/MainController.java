package com.lanou.controller;

import com.lanou.service.ConsumerService;
import com.lanou.service.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by dllo on 17/11/29.
 */
@Controller
public class MainController {
    @Resource
    private Destination queueDestination;
    @Resource
    private ProducerService producerService;
    @Resource
    private ConsumerService consumerService;

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }


    @RequestMapping(value = "/producer")
    public String producerPage() {
        return "producer";
    }

    @RequestMapping(value = "/consumer")
    public String consumerPage(Model model) throws JMSException {
        TextMessage textMessage = consumerService.receive(queueDestination);
        if (textMessage != null) {
            model.addAttribute("textMessage", textMessage.getText());
        } else {
            model.addAttribute("textMessage", "没有新消息");
        }
        return "consumer";
    }

    @RequestMapping(value = "/sendmsg")
    public String sendMsg(@RequestParam("message") String msg) {
        System.out.println("---->消息发送到jms<------");

        producerService.sendMessage(queueDestination, msg);
        return "home";
    }

}
