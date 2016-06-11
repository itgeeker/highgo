package me.highgo.back;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class SolrCloudTest {
    // zookeeper地址
    private static String zkHostString = "192.168.18.147:2181,192.168.18.140:2181,192.168.18.145:2181";
    // collection默认名称，比如我的solr服务器上的collection是collection2_shard1_replica1，就是去掉“_shard1_replica1”的名称
    private static String defaultCollection = "goodslist_1";
    // 客户端连接超时时间
    private static int zkClientTimeout = 3000;
    // zookeeper连接超时时间
    private static int zkConnectTimeout = 3000;

    // cloudSolrServer实际
    private CloudSolrServer cloudSolrServer;

    // 测试方法之前构造 CloudSolrServer
    @Before
    public void init() {
        cloudSolrServer = new CloudSolrServer(zkHostString);
        cloudSolrServer.setDefaultCollection(defaultCollection);
        cloudSolrServer.setZkClientTimeout(zkClientTimeout);
        cloudSolrServer.setZkConnectTimeout(zkConnectTimeout);
        cloudSolrServer.connect();
    }

    // 向solrCloud上创建索引
    @Test
    public void testCreateIndexToSolrCloud() throws SolrServerException,IOException {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "100001");
        document.addField("picture", "2014032613103438.png");
        document.addField("catalog_name", "测试商品");
        document.addField("price", "2388");
        document.addField("catalog", "20");
        document.addField("name", "花儿朵朵彩色金属门后挂 8钩免钉门背挂钩 测试商品");
        document.addField("release_time", new Date());
        cloudSolrServer.add(document);
        cloudSolrServer.commit();
    }

    // 搜索索引
    @Test
    public void testSearchIndexFromSolrCloud() throws Exception {

        SolrQuery query = new SolrQuery();
//        query.setQuery("name:花儿朵朵彩色金属门后挂 8钩免钉门背挂钩2066");
        query.setQuery("name:'测试'");
        try {
            QueryResponse response = cloudSolrServer.query(query);
            SolrDocumentList docs = response.getResults();

            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());

            for (SolrDocument doc : docs) {
                Date release_time = (Date) doc.getFieldValue("release_time");
                String name = (String) doc.getFieldValue("name");
                String id = (String) doc.getFieldValue("id");
                System.out.println("id: " + id);
                System.out.println("name: "+name);
                System.out.println("release_time: " + release_time);
                System.out.println();
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknowned Exception!!!!");
            e.printStackTrace();
        }
    }

    // 删除索引
    @Test
    public void testDeleteIndexFromSolrCloud() throws SolrServerException, IOException {

        // 根据id删除
        UpdateResponse response = cloudSolrServer.deleteById("zhangsan");
        // 根据多个id删除
        // cloudSolrServer.deleteById(ids);
        // 自动查询条件删除
        // cloudSolrServer.deleteByQuery("product_keywords:教程");
        // 提交
        cloudSolrServer.commit();
    }
}