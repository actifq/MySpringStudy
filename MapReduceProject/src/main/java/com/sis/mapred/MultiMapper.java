package com.sis.mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.*;


public class MultiMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final IntWritable one=new IntWritable(1);
	private Text result=new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
			StringTokenizer st=new StringTokenizer(value.toString());
			while(st.hasMoreTokens()){
				result.set(st.nextToken());
				context.write(result, one);
				
			}
	}
}
