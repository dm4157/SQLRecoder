package org.syy.sqlrecoder.dao;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.syy.sqlrecoder.dao.contract.IWriter;
import org.syy.sqlrecoder.entity.SQLRecoder;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

/**
 * 默认的写入者
 * 写入的媒介是本地文件索引
 * Created by Administrator on 2015/2/9.
 */
@Repository
public class DefaultIndexWriter implements IWriter{

    @Autowired
    private File indexDir;

    private IndexWriter indexWriter;

    /**
     * 初始化，打开索引
     * 创建索引工具
     */
    @PostConstruct
    public void init() {
        try {
            Directory dir = FSDirectory.open(indexDir);
            Analyzer analyzer = new IKAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LATEST, analyzer);
            indexWriter = new IndexWriter(dir, iwc);
            System.out.println("索引已准备好，安心写吧。。。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束时别忘了释放资源
     */
    @PreDestroy
    public void destroy() {
        try {
            indexWriter.close();
            System.out.println("明天没戏了。。。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将SQL记录保存到本地索引中
     * Field选项是索引、存储、高亮
     * @param recoder   SQL记录
     */
    @Override
    public void write(SQLRecoder recoder) {
        Document doc = new Document();
        doc.add(new TextField("description", recoder.getDescription(), Field.Store.YES));
        doc.add(new TextField("sql", recoder.getSql(), Field.Store.YES));
        doc.add(new LongField("timeToken", recoder.getTimeToken(), Field.Store.YES));
        try {
            indexWriter.addDocument(doc);
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
