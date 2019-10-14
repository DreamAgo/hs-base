package cn.loverot.base.action;

import cn.loverot.base.constant.e.BaseEnum;
import cn.loverot.base.entity.BaseEntity;
import cn.loverot.base.entity.ResultJson;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class BaseAction {
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    protected void outJson(HttpServletResponse response, BaseEnum code, boolean flag, String msg, Object data) {
        try {
            response.setContentType("application/json;charset=utf-8");
            ResultJson result = new ResultJson(code.toInt(),flag,msg,data);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(JSONObject.toJSON(result));
            out.flush();
            out.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    protected void outJson(HttpServletResponse response, BaseEnum code, boolean flag, String msg) {
        this.outJson(response, code, flag, msg, (Object)null);
    }

    protected void outJson(HttpServletResponse response, boolean flag, String msg) {
        this.outJson(response, (BaseEnum)null, flag, msg, (Object)null);
    }

    protected void outJson(HttpServletResponse response, BaseEnum code, boolean flag) {
        this.outJson(response, code, flag, (String)null, (Object)null);
    }

    protected void outJson(HttpServletResponse response, boolean flag) {
        this.outJson(response, (BaseEnum)null, flag, (String)null, (Object)null);
    }

    protected void outJson(HttpServletResponse response, Object jsonDataStr) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(jsonDataStr);
            out.flush();
            out.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    protected void outJson(HttpServletResponse response, BaseEntity entity) {
        this.outJson(response, (Object)JSONObject.toJSONString(entity));
    }

    protected void outJson(HttpServletResponse response, List list) {
        this.outJson(response, (Object)JSONArray.toJSONString(list));
    }



    protected void outJson(HttpServletResponse response, Object obj, final String... filters) {
        PropertyFilter filter = new PropertyFilter() {
            @Override
            public boolean apply(Object source, String name, Object value) {
                List list = Arrays.asList(filters);
                return !list.contains(name);
            }
        };
        SerializeWriter sw = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(sw);
        serializer.getPropertyFilters().add(filter);
        serializer.write(obj);
        this.outJson(response, (Object)sw);
    }

    protected void outJson(HttpServletResponse response, List list, String dateFmt) {
        this.outJson(response, (Object)JSONArray.toJSONStringWithDateFormat(list, dateFmt, new SerializerFeature[0]));
    }

    protected void redirect(HttpServletResponse response, String path) {
        this.outString(response, "<script>location.href='" + path + "'</script>");
    }

    protected void outString(HttpServletResponse response, Object dataStr) {
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(dataStr);
            out.flush();
            out.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

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
     * 获取当期项目物理路径
     *
     * @param request
     *            HttpServletRequest对象
     * @param filePath
     *            相对路径文件夹
     * @return 返回当期项目物理路径
     */
    protected String getRealPath(HttpServletRequest request, String filePath) {
        return request.getServletContext().getRealPath("") + File.separator + filePath;
    }
    /**
     * 获取请求客户端ip
     *
     * @param request
     * @return ip地址
     */
    public String getIp(HttpServletRequest request) {
        String ipAddress = null;
        // ipAddress = this.getRequest().getRemoteAddr();
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
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
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
