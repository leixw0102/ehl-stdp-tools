package com.ehl.es4sql;

import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ElasticSearchFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchFactory.class);

	private static ConcurrentHashMap<Integer, Client> esClients;

	static {
		try {
			esClients = new ConcurrentHashMap<Integer, Client>();
		} catch (Exception e) {
			LOGGER.error("init esfactory error", e);
		}
	}

	public static void close(String url) {
		Client remove = esClients.remove(url.hashCode());
		if (remove != null) {
			remove.close();
		}
	}


	public static Client buildConnection(Properties info){
		String name = info.getProperty("es.name");
		assert Strings.isNullOrEmpty(name);
		String ip = info.getProperty("es.ip");
		assert Strings.isNullOrEmpty(ip);
		String port=info.getProperty("es.port");
		assert Strings.isNullOrEmpty(port);
		String index = info.getProperty("es.index");

		String url = name+"://"+ip+":"+port;

		int hashCode = url.hashCode();
		Client client = esClients.get(hashCode);
		if (client != null) {
			return client;
		}
		synchronized (ElasticSearchFactory.class){
			client = esClients.get(hashCode);
			if (client == null) {
				LOGGER.info("create ESClient...url:" + url + ",info:" + info.toString());
				client = buildClient(name,ip,port);
				if (client == null) {
					throw new RuntimeException(
							String.format("Create ES error url : %s, info : %s", url, JSON.toJSONString(info)));
				}
				esClients.put(hashCode, client);
			} else {
				client = esClients.get(hashCode);
			}
			return client;
		}

	}

	/**
	 *
	 * @param name
	 * @param ip
	 * @param port
     * @return
     */
	private static Client buildClient(String name, String ip, String port) {
		Settings settings = Settings.settingsBuilder().put("cluster.name", name).build();
		TransportClient transportClient = TransportClient.builder().settings(settings).build();
		String[]ips = ip.split(",");
		for(String tempIp:ips){
			transportClient.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(tempIp,Integer.parseInt(port))));
		}
		return transportClient;
	}

	public static Client buildConnection(){
		try {
			Properties info = new Properties();
			info.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("es.properties"));
			return buildConnection(info);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}


}
