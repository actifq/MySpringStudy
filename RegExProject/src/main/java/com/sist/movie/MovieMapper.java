package com.sist.movie;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/*
 * 긍정
 * 
 * 강렬,새로운,기대,호평,다시보고 싶은,완성,재밌다,독창적인,좋은,추천,대박
 * 유쾌,재미,매력,사랑,애틋한,기분좋은,빠져드는
 * 긍정,스케일,코믹스,인기,등장인물도 많고,핫한,즐거움,최초,뜨겁,반가운,재미,좋은,개그,뛰어난,♡;
 * 좋았,좋은영화,씹덕했다,행복,재미있었다,좋았습니다,
 * 괜찮,궁금,재미,귀여운,좋아,호사,즐겁게,유쾌한,생동감,유머,경의로운,감사,귀여워,열광적,꿀잼,최고,즐거워,사니나게,즐겨
 * 고마엉,사랑,아리게,반해,감동,좋은,아름다운,눈물,재밌,추천
 * 
 * 
 */

public class MovieMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final IntWritable one = new IntWritable(1);
	private Text result = new Text();
	String[] yn={"유쾌[가-핳]","재미","매력","기분좋[가-핳]","호평","좋은","최고","사랑","좋[가-핳]","추천",
					"재미없[가-핳]","별로","화[가-핳]","그닥","허무","암[가-핳]","최악[가-핳]","망[가-핳]","허무","나쁘[가-핳]","엉망"
	};
	
	Pattern[] pattern=new Pattern[yn.length];
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		for(int i=0;i<yn.length;i++){
			pattern[i]=Pattern.compile(yn[i]);
		}
		
		Matcher[] matcher=new Matcher[yn.length];
		for(int i=0;i<yn.length;i++){
			matcher[i]=pattern[i].matcher(value.toString());
			
			while(matcher[i].find())
				if(i>=0 && i<=9){
					result.set("긍정");
				}
				else{
					result.set("부정");
				}
			
				context.write(result, one);
			
		}
		
	}
	
	
}

























