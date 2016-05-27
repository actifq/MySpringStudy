package com.sist.movie;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.rosuda.REngine.Rserve.RConnection;

import com.sist.movie.*;


public class MovieDriver {

	public static void main(String[] args) throws Exception{
		File dir=new File("./output1");
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
		job.setJarByClass(MovieDriver.class);
		job.setMapperClass(MovieMapper.class);
		job.setReducerClass(MovieReducer.class);
		// 파일넘기기
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// 결과값 받기
		FileInputFormat.addInputPath(job, new Path("/home/sist/desc.txt"));
		FileOutputFormat.setOutputPath(job, new Path("./output1"));
		
		job.waitForCompletion(true);
		
		rGraph();
	}
	
	public static void rGraph(){
		try{
			
			RConnection rc=new RConnection();
			
			rc.voidEval("yn<-read.table(\"/home/sist/javaStudy/RegExProject/output/part-r-00000\")");
			rc.voidEval("png(\"/home/sist/yn.png\")");
			rc.voidEval("pie(yn$V2,labels=paste(yn$V1,\",\",yn$V2),col=rainbow(2))");
			rc.voidEval("dev.off()");
			rc.close();
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/*public static void rGraph()
    {
        try
        {
            RConnection rc=new RConnection();
            rc.voidEval("yn<-read.table(\"/home/sist/javaStudy/RegExProject/output1/part-r-00000\")");
            rc.voidEval("png(\"/home/sist/yn.png\")");
            rc.voidEval("pie(yn$V2,labels=paste(yn$V1,\"\n\",yn$V2),col=rainbow(2))");
            rc.voidEval("dev.off()");
            rc.close();
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    } */

}



















