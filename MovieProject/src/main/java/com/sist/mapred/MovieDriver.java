package com.sist.mapred;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class MovieDriver {

		public static void main(String[] args) throws Exception{
			
			File dir=new File("./output");
			if(dir.exists())
			{
				File[] files=dir.listFiles();
				for(File f:files)
				{
					f.delete();
				}
				dir.delete();
			}
			Configuration conf= new Configuration();
			Job job=new Job(conf,"MovieFeelCount");
			job.setJarByClass(MovieDriver.class);
			job.setMapperClass(MovieMapper.class);
			job.setReducerClass(MovieReducer.class);
			
			// 결과값
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			FileInputFormat.addInputPath(job, new Path("/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieProject/desc.txt"));
			FileOutputFormat.setOutputPath(job, new Path("./output"));
			job.waitForCompletion(true);
		}
}
