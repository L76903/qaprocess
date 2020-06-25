package com.qaprocess.config;

import com.sun.xml.internal.ws.api.message.HeaderList;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.coyote.http11.Http11NioProtocol;
import org.aspectj.lang.annotation.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Configuration
public class TomcatConfig {
    //端口范围
    @Value("${my.server.port-scope:@null}")
    String myPortScope;
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                if (!("@null".equals(myPortScope) || myPortScope.isEmpty())) {
                    //获取可用端口，指定端口范围，如果返回-1则范围内没有可用的，此时会抛出IllegalArgumentException
                    ConnectorUtil connectorUtil=new ConnectorUtil();
                    int port = connectorUtil.findAvailablePort(myPortScope);
                    if (connectorUtil.getNotPort().size()>0){
                        log.warn("Port can't use:"+connectorUtil.getNotPort());
                    }
                    System.gc();
                    if (port < 0) {
                        throw new IllegalArgumentException("no available port !");
                    } else {
                        connector.setPort(port);
                        System.setProperty("server.port",String.valueOf(port));
                    }
                }
                //一些调优参数，别处抄来的
                Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
                protocol.setMaxConnections(200);
                protocol.setMaxThreads(200);
                protocol.setSelectorTimeout(3000);
                protocol.setSessionTimeout(3000);
                protocol.setConnectionTimeout(3000);
                if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                    //-1 means unlimited
                    ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);

                }

            }
        });
        return tomcat;
    }
}

/**
 * 工具类用于检查端口可用性
 */
@Slf4j
class ConnectorUtil {
    private static List notPort=new LinkedList<Integer>();

    public List getNotPort() {
        return notPort;
    }

    private void bindPort(String host, int port) throws Exception {
        Socket s = new Socket();
        s.bind(new InetSocketAddress(host, port));
        s.close();

    }
    public boolean isPortAvailable(int port) {
        Socket s = new Socket();
        try {
            bindPort("0.0.0.0", port);
            bindPort(InetAddress.getLocalHost().getHostAddress(), port);
            return true;
        } catch (Exception e) {
            notPort.add(port);
            return false;
        }
    }


    public int findAvailablePort(int start, int end){
        int s=start<end?start:end;
        int e=start>end?start:end;

        for (int i=s;i<e;i++)
        {
            if(isPortAvailable(i)) {
                return i;
            }
        }
        return -1;
    }
    public int findAvailablePort(String portScope){
        int port= -1;
        String[] ps=portScope.split("-");
        if (ps.length!=2){
            return port;
        }

        try {
            port=new ConnectorUtil().findAvailablePort(Integer.parseInt(ps[0]),Integer.parseInt(ps[1]));
        }catch (Exception e){
            return port;
        }
        return port;
    }
}