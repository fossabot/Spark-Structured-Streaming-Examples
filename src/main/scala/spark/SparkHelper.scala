package spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object SparkHelper {
  def getAndConfigureSparkSession() = {
    val conf = new SparkConf()
      .setAppName("Structured Streaming from Parquet to Cassandra")
      .setMaster("local[2]")
      .set("spark.cassandra.connection.host", "127.0.0.1")
      .set("spark.sql.streaming.checkpointLocation", "checkpoint")
      .set("es.nodes", "localhost") // full config : https://www.elastic.co/guide/en/elasticsearch/hadoop/current/configuration.html
      .set("es.index.auto.create", "true") //https://www.elastic.co/guide/en/elasticsearch/hadoop/current/spark.html
      .set("es.nodes.wan.only", "true")

    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    SparkSession
      .builder()
      .getOrCreate()
  }

  def getSparkSession() = {
    SparkSession
      .builder()
      .getOrCreate()
  }
}
