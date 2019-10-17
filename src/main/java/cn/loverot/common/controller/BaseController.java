package cn.loverot.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BaseController {
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected String getUrl(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() == 80) {
            basePath = basePath + path;
        } else {
            basePath = basePath + ":" + request.getServerPort() + path;
        }

        return basePath;
    }

    protected String getDomain(HttpServletRequest request) {
        String path = request.getContextPath();
        String domain = request.getServerName();
        if (request.getServerPort() == 80) {
            domain = domain + path;
        } else {
            domain = domain + ":" + request.getServerPort() + path;
        }

        return domain;
    }

    protected String getHost(HttpServletRequest request) {
        String basePath = request.getServerName();
        if (request.getServerPort() != 80) {
            basePath = basePath + ":" + request.getServerPort();
        }

        return basePath;
    }

    protected String getHostIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress().toString();
        } catch (UnknownHostException var3) {
            var3.printStackTrace();
            return "";
        }
    }

    /**
     * 获取请求客户端ip
     *
     * @param request
     * @return ip地址
     */
    public String getIp(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
    /**
     * 获取对应ip地址的mac地址
     *
     * @param ip
     * @return mac地址
     */
    public String getMACAddress(String ip) {
        String str = "";
        String macAddress = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    if (str.indexOf("MAC Address") > 1) {
                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }



}
