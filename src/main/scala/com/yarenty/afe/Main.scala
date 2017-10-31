package com.yarenty.afe

import org.apache.spark.h2o.{H2OConf, H2OContext}
import org.apache.spark.{SparkConf, SparkSessionUtils}

object Main {

  def main(args: Array[String]) {

    val conf: SparkConf = H2OConf.checkSparkConf(
      new SparkConf()
        .setAppName("Sparkling Water Driver")
        .setIfMissing("spark.master", sys.env.getOrElse("spark.master", "local[*]"))
        .set("spark.ext.h2o.repl.enabled", "true"))

    val spark = SparkSessionUtils.createSparkSession(conf)
    // Start H2O cluster only
    val hc = H2OContext.getOrCreate(spark.sparkContext)

    println(hc)

    // Infinite wait
    this.synchronized(while (true) {
      wait()
    })

  }

}
