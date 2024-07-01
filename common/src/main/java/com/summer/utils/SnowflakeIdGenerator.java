package com.summer.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * @Description
 * @Author pipe
 * @Date 2024/5/30 19:28
 */
public class SnowflakeIdGenerator {

    // 开始时间戳 (2020-01-01)
    private final long twepoch = 1577836800000L;

    // 机器ID所占的位数
    private final long workerIdBits = 5L;

    // 数据中心ID所占的位数
    private final long datacenterIdBits = 5L;

    // 支持的最大机器ID，结果是31
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    // 支持的最大数据中心ID，结果是31
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    // 序列在ID中占的位数
    private final long sequenceBits = 12L;

    // 机器ID向左移12位
    private final long workerIdShift = sequenceBits;

    // 数据中心ID向左移17位
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    // 时间戳向左移22位
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    // 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    // 工作机器ID(0~31)
    private long workerId;

    // 数据中心ID(0~31)
    private long datacenterId;

    // 毫秒内序列(0~4095)
    private long sequence = 0L;

    // 上次生成ID的时间戳
    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator() {
        this.workerId = getMaxWorkerId(maxDatacenterId, maxWorkerId);
        this.datacenterId = getDatacenterId(maxDatacenterId);
    }

    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuilder mpid = new StringBuilder();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (StringUtils.isNotEmpty(name)) {

            mpid.append(name.split("@")[0]);
        }

        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {

            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                if (null != mac) {
                    id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                    id = id % (maxDatacenterId + 1);
                }
            }

            String ipv4 = getLocalAddress();

            id = ipv4ToLong(ipv4);

            id = id & 0x3ff;//经测试最大值为1023
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private static String getLocalAddress() {
        try {
            // Traversal Network interface to get the first non-loopback and non-private address
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            ArrayList<String> ipv4Result = new ArrayList<String>();
            //ArrayList<String> ipv6Result = new ArrayList<String>();
            while (enumeration.hasMoreElements()) {
                final NetworkInterface networkInterface = enumeration.nextElement();
                final Enumeration<InetAddress> en = networkInterface.getInetAddresses();
                while (en.hasMoreElements()) {
                    final InetAddress address = en.nextElement();
                    if (!address.isLoopbackAddress()) {
                        if (address instanceof Inet6Address) {
                            //ipv6Result.add(address.getHostAddress());
                        } else {
                            ipv4Result.add(address.getHostAddress());
                        }
                    }
                }
            }

            // prefer ipv4
            if (!ipv4Result.isEmpty()) {
                for (String ip : ipv4Result) {
                    if (ip.startsWith("127.0") || ip.startsWith("192.168")) {
                        continue;
                    }

                    return ip;
                }

                return ipv4Result.get(ipv4Result.size() - 1);
            }
            final InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    private static long ipv4ToLong(String strIp) {
        String[] ip = strIp.split("\\.");
        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }

    // 生成唯一ID
    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    // 阻塞到下一个毫秒，直到获得新的时间戳
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    // 返回当前时间戳
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator();
        SnowflakeIdGenerator idGenerator1 = new SnowflakeIdGenerator();
        for (int i = 0; i < 10; i++) {
            System.out.println(idGenerator.nextId());
            System.out.println(idGenerator1.nextId());
        }
    }
}
