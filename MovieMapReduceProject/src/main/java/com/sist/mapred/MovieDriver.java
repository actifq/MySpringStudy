package com.sist.mapred;
import java.io.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.stereotype.Component;
@Component
public class MovieDriver {
	public void movieMapReduce()
	{
		try
		{
			Configuration conf=new Configuration();
			Job job=new Job(conf,"MovieFeelCount");
			job.setJarByClass(MovieDriver.class); //실행파일
			job.setMapperClass(MovieMapper.class);
			job.setReducerClass(MovieReducer.class);// map,reduce => 실행하기 위한 장비
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			FileInputFormat.addInputPath(job, new Path("/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/desc.txt")); //파일명을 줘야함
			File dir=new File("/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/output");
			if(dir.exists())
			{
				File[] files=dir.listFiles(); //파일 전체 읽음
				for(File f:files)
				{
					f.delete();
				}
				dir.delete();
			}
			FileOutputFormat.setOutputPath(job, new Path("/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/output")); //폴더명으로 줘야함
			job.waitForCompletion(true); //job 실행
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
