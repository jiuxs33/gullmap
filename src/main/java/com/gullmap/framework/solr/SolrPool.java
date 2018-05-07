package com.gullmap.framework.solr;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrPool {

	private static List<SolrClient> pooledClients = Collections.synchronizedList(new LinkedList<SolrClient>());
	
	public SolrPool(String solrHost){
		init(solrHost,2000);
	}

	private void init(String solrHost, int count) {
		for(int i=0;i<count;i++){
			SolrClient client = new HttpSolrClient(solrHost);
			pooledClients.add(client);
		}
	}
	
	public synchronized SolrClient getClient(){
		if(pooledClients.size()<=0){
            throw new RuntimeException("获取连接失败，连接池为空");
        }
		SolrClient client = pooledClients.get(0);
    	pooledClients.remove(0);
        return client;    
	}
	
	public void close(SolrClient client) {
		pooledClients.add(client);
	}

	public void closeAllClient() {
		try{
			for(SolrClient client : pooledClients){
				client.close();
			}
			pooledClients.clear();
		}catch(IOException e){}
	}
}
