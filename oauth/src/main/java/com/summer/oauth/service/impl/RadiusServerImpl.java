package com.summer.oauth.service.impl;

import org.springframework.stereotype.Component;
import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusException;
import org.tinyradius.util.RadiusServer;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author pipe
 * @Date 2024/5/23 11:55
 */
@Component
public class RadiusServerImpl extends RadiusServer {

    @PostConstruct
    public void startServer() {
        try {
            this.setAuthPort(1812);
            this.setAcctPort(1813);
            this.start(true, true);
        } catch (Exception e) {
//            logger.severe("Failed to start RADIUS server: " + e.getMessage());
        }
    }

    @Override
    public String getSharedSecret(InetSocketAddress inetSocketAddress) {
        System.out.println("secret");
        return "secret";
    }

    @Override
    public String getUserPassword(String s) {
        return "password";
    }

    @Override
    public RadiusPacket accessRequestReceived(AccessRequest accessRequest, InetSocketAddress client) throws RadiusException {
        System.out.println("auth");
        System.out.println("Received Access-Request from " + client.getAddress());
        List attributes = accessRequest.getAttributes();
        // Parse RADIUS attributes
//        accessRequest.getAttributes().forEach(attribute -> {
//            System.out.println("Attribute: " + attribute() + " Value: " + attribute.getValue());
//        });

        // Validate user credentials
        String userName = accessRequest.getUserName();
        String password = accessRequest.getUserPassword();
        if ("user".equals(userName) && "password".equals(password)) {
            return createAccessAccept(accessRequest, client);
        } else {
//            return createAccessReject(accessRequest, client);
        }
        return null;
    }

    private RadiusPacket createAccessAccept(AccessRequest accessRequest, InetSocketAddress requestCtx) {

        RadiusPacket accessAccept = new RadiusPacket(RadiusPacket.ACCESS_ACCEPT,accessRequest.getPacketIdentifier());
        List<Map<String,String>> attrs= new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("Framed-IP-Address","192.168.1.100");

        attrs.add(map);
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("Framed-Protocol", "PPP");
        attrs.add(map1);

        // 你可以根据需要添加更多的属性
        // accessAccept.setAttribute("Attribute-Name", "Attribute-Value");

        return accessAccept;
    }

//    private RadiusPacket createAccessReject(AccessRequest accessRequest, InetSocketAddress requestCtx) {
//        RadiusPacket accessReject = new RadiusPacket(RadiusPacket.ACCESS_REJECT, accessRequest.getIdentifier());
//        return accessReject;
//    }
}
