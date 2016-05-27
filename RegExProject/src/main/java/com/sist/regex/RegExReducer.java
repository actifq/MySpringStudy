package com.sist.regex;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RegExReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	private IntWritable res = new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
	
		int sum=0;
		for(IntWritable v:values){
			sum+=v.get();
		}
		
		res.set(sum);
		context.write(key,res);
		
	}
}
