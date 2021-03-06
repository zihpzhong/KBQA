package com.qdcz.conf;

import com.hankcs.hanlp.utility.Predefine;
import com.qdcz.mongo.conf.MongoConfiguration;
import com.qdcz.graph.neo4jcypher.conf.Neo4jConfiguration;
import com.qdcz.index.elsearch.conf.ELKConfig;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * Created by hadoop on 17-6-27.
 *
 */
@WebListener
public class LoadConfigListener implements ServletContextListener {



    @Value("${source_dir}")
    private String source_dir;



    @Value("${hanlp_path}")
    private String hanlp_path;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            Predefine.HANLP_PROPERTIES_PATH = hanlp_path+"hanlp.properties";
            //------------加载neo4j的配置------------
            System.out.println("------------加载neo4j的配置------------");
            Neo4jConfiguration neo4jConfiger=new Neo4jConfiguration();
            neo4jConfiger.load(LoadConfigListener.class.getResourceAsStream(source_dir+"neo4j.properties"));

            System.out.println("------------加载MongoDB配置文件------------");
            MongoConfiguration mongoConf = new MongoConfiguration();
            mongoConf.load(LoadConfigListener.class.getResourceAsStream(source_dir+"mongo.properties"));

            System.out.println("------------加载ElaSearch配置文件------------");
            ELKConfig elkConfig = new ELKConfig();
            elkConfig.load(LoadConfigListener.class.getResourceAsStream(source_dir+"elasearch.properties"));

            System.out.println("------------加载Database配置文件------------");
            DatabaseConfiguration databaseConf = new DatabaseConfiguration();
            databaseConf.load(LoadConfigListener.class.getResourceAsStream(source_dir+ "database.xml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getSource_dir() {
        return source_dir;
    }

    public void setSource_dir(String source_dir) {
        this.source_dir = source_dir;
    }
    public String getHanlp_path() {
        return hanlp_path;
    }

    public void setHanlp_path(String hanlp_path) {
        this.hanlp_path = hanlp_path;
    }

}