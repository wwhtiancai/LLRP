package org.llrp.ltk.test;

import org.llrp.ltk.types.LLRPMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;


public class Publisher {

    private static final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private final Jedis publisherJedis;
    private final String channel;

    public Publisher(Jedis publisherJedis, String channel) {
        this.publisherJedis = publisherJedis;
        this.channel = channel;
    }

    public void start(LLRPMessage message) {
        logger.info("Type your message start");
        try {
                    publisherJedis.publish(channel, message.toXMLString());

        } catch (Exception e) {
            logger.error("IO failure while reading input, e");
        }
    }
}