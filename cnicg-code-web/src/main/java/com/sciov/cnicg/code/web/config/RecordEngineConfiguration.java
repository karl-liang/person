package com.sciov.cnicg.code.web.config;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.sciov.cnicg.code.web.demo.decision.LocationMatchDecisionStrategy;
import com.sciov.cnicg.code.web.demo.decision.LogWarnRouter;

import cn.cnicg.code.recordengine.base.BaseDecisionRouterManager;
import cn.cnicg.code.recordengine.base.BaseDecisionStrategyManager;
import cn.cnicg.code.recordengine.decision.DefultDecisionStrategy;
import cn.cnicg.code.recordengine.decision.SuccRouter;
import cn.cnicg.code.recordengine.decision.WarnRouter;
import cn.cnicg.code.recordengine.enums.DecisionResultEnum;
import cn.cnicg.code.recordengine.intf.IDecisionRouterManager;
import cn.cnicg.code.recordengine.intf.IDecisionStrategyManager;
import cn.cnicg.code.recordengine.intf.IRouteHandler;

@Configuration
public class RecordEngineConfiguration {

	
	@Bean
	public IDecisionRouterManager getIDecisionRouterManager(IRouteHandler routeHandler) {
		BaseDecisionRouterManager routerManager = new BaseDecisionRouterManager();
		SuccRouter router = new SuccRouter(routeHandler);
		WarnRouter warnRouter = new LogWarnRouter(routeHandler);
		routerManager.putRouter(DecisionResultEnum.SUCC.getKey(), router);
		routerManager.putRouter(DecisionResultEnum.WARN.getKey(), warnRouter);
		return routerManager;
	}
	
	
	@Bean
	public IDecisionStrategyManager getIDecisionStrategyManager() {
		
		BaseDecisionStrategyManager strategyManager = new BaseDecisionStrategyManager();
		strategyManager.addDecisionStrategy(new DefultDecisionStrategy());
		strategyManager.addDecisionStrategy(new LocationMatchDecisionStrategy());
		return strategyManager;
	}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(getClientHttpRequestFactory());
    }

    public HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(acceptsUntrustedCertsHttpClient());
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);
        return requestFactory;
    }

    /**
     * 信任证书，支持调用https
     * @return
     */
    public static CloseableHttpClient acceptsUntrustedCertsHttpClient() {
        HttpClientBuilder b = HttpClientBuilder.create();
        try {
            // setup a Trust Strategy that allows all certificates.
            // lambda 表达式实现
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (var1, var2) ->  true
            ).build();
            b.setSSLContext(sslContext);

            // don't check Hostnames, either.
            //      -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if you don't want to weaken
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

            // here's the special part:
            //      -- need to create an SSL Socket Factory, to use our weakened "trust strategy";
            //      -- and create a Registry, to register it.
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslSocketFactory)
                    .build();

            //create connection-manager using our Registry. allows multi-threaded use
            PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            connMgr.setMaxTotal(200);
            connMgr.setDefaultMaxPerRoute(100);
            b.setConnectionManager( connMgr);
        } catch (Exception e) {
        }
        // finally, build the HttpClient;
        CloseableHttpClient client = b.build();
        return client;
    }
}
