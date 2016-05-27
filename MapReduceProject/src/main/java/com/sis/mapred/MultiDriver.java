package com.sis.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MultiDriver {

	public static void main(String[] args) throws Exception{
		Configuration conf=new Configuration();
		Job job=new Job(conf,"MultiMapReduce");
		
		job.setJarByClass(MultiDriver.class);
		job.setMapperClass(MultiMapper.class);
		job.setReducerClass(MultiReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path("./test"));
		FileOutputFormat.setOutputPath(job, new Path("./output"));
		job.waitForCompletion(true);
	} 

}
