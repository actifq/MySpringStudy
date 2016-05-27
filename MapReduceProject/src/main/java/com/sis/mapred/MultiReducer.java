package com.sis.mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MultiReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	private IntWritable res=new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum=0;
		for(IntWritable v:values){
			//IntWritable에서 정수형을 가져오는거
			sum+=v.get();
		}
		res.set(sum);
		context.write(key,res);
	}
}
