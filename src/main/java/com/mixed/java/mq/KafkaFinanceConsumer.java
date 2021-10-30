//package com.mixed.java.mq;
//
//import com.shopline.fund.common.alarm.starter.WorkWeChat;
//import com.shopline.fund.common.alarm.starter.enums.AlertLevelEnum;
//import com.shopline.fund.xpay_binlog_kafka.constants.Constants;
//import com.shopline.fund.xpay_binlog_kafka.mq.stream.FinanceOrderSubscribeStream;
//import com.shopline.fund.xpay_binlog_kafka.service.ProcessFinanceRecordService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.messaging.Message;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableBinding(FinanceOrderSubscribeStream.class)
//public class KafkaFinanceConsumer {
//
//    @Value("${wx.work.name}")
//    private String name;
//    @Value("${kafka.skipMessage}")
//    private Boolean skipMessage;
//    @Value("${kafka.nackTime}")
//    private Long nackTime;
//    @Autowired
//    private ProcessFinanceRecordService processFinanceRecordService;
//    @Autowired
//    private WorkWeChat workWeChat;
//
//    private static Logger logger =  LoggerFactory.getLogger(KafkaFinanceConsumer.class);
//
//    public KafkaFinanceConsumer() {
//        logger.info("KafkaFinanceConsumer 创建成功");
//    }
//
//    @StreamListener(FinanceOrderSubscribeStream.FINANCE_ORDER_SUBSCRIBE_TOPIC)
//    public void process(Message message) {
//        logger.info(" 【金融网络】 receive message:{}", message.getPayload());
//        Acknowledgment acknowledgment = message.getHeaders().get("kafka_acknowledgment", Acknowledgment.class);
//        if (acknowledgment == null) {
//            logger.warn("【金融网络】 acknowledgement  is null");
//            return;
//        }
//
//        try {
//            logger.info("【金融网络】 start to handle request {}", message.getHeaders().getId());
//            String payData = (String) message.getPayload();
//
//            boolean result = processFinanceRecordService.process(payData);
//            if (result) {
//                acknowledgment.acknowledge();
//            } else {
//              workWeChat.sendWithEnv(Constants.PROCESS_XPAY_GATEWAY_ERROR, AlertLevelEnum.ERROR, "数据处理失败: " + payData, name);
//                logger.error(" 【金融网络】 error failed in metadata {}", payData);
//                if (skipMessage) {
//                    acknowledgment.acknowledge();
//                } else {
//                    acknowledgment.nack(nackTime);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("【金融网络】 process error ", e);
//          workWeChat.sendWithEnv(Constants.PROCESS_XPAY_GATEWAY_ERROR, AlertLevelEnum.ERROR, "数据处理失败: " + String.valueOf(message.getPayload()), name);
//            logger.error("【金融网络】 process error ", e);
//            if (skipMessage){
//                acknowledgment.acknowledge();
//            }else{
//                acknowledgment.nack(nackTime);
//            }
//        }
//
//    }
//}