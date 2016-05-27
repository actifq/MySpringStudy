package com.sist.regex;

import java.io.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RegExDriver {

	public static void main(String[] args) throws Exception{
		File dir=new File("./output");
		if(dir.exists()){
			
			File[] files=dir.listFiles();
			for(File f:files){
				f.delete(); //-rf
			}
			dir.delete(); //rm
		}
		// hadoop
		Configuration conf=new Configuration();
		// JOB
		Job job=new Job(conf,"RegExCount");
		// TOOL
		job.setJarByClass(RegExDriver.class);
		job.setMapperClass(RegExMapper.class);
		job.setReducerClass(RegExReducer.class);
		// 파일넘기기
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// 결과값 받기
		FileInputFormat.addInputPath(job, new Path("/home/sist/access_log"));
		FileOutputFormat.setOutputPath(job, new Path("./output"));
		
		job.waitForCompletion(true);
	}

}
