package com.sist.regex;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 
 ^ 문자열의 시작 ^A	A%
 $ 문자열의 종료 T$	$T
 . 임의의 한 문자 (문자의 종류 가리지 않음) 단, \ 는 넣을 수 없음
 * 앞 문자가 없을 수도 무한정 많을 수도 있음
 + 앞문자가 한개 이상
 ? 앞 문자가 없어나 한개
 [] 문자의 집합이나 범위를 나타내며 두 문자 사이는 - 기호로 범위를 나타낸다. []내에서 ^가 선행하여 존재하면 not 을 나타낸다.
	[A-Z] [a-z] [0-9] [가-핳]
 {} 횟수 또는 범위를 나타낸다.
 	^A{a-z}
 	^[a-z]
 () 소괄호 안의 문자를 하나의 문자로 인식 
 | 패턴 안에서 or 연산을 수행할 때 사용
 \s 공백 문자
 \S 공백 문자가 아닌 나머지 문자
 \w 알파벳이나 숫자
 \W  알파벳이나 숫자를 제외한 문자
 \d  숫자 [0-9]와 동일
 \D  숫자를 제외한 모든 문자
 \   정규표현식 역슬래시(\)는 확장 문자 역슬래시 다음에 일반 문자가 오면 특수문자로 취급하고 역슬래시 다음에 특수문자가 오면 그 문자 자체를 의미
 (?i)  앞 부분에 (?i) 라는 옵션을 넣어주면 대소문자를 구분하지 않음

*/


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RegExMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final IntWritable one = new IntWritable(1);
	
	private Text result = new Text();
	String regex1="(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})"; //==([0-9]{1,3})  <== ip
	   /*
	    *       07/Mar/2004:16:06:51
	    */
	   String regex2="\\d{2}/\\w{3}/\\d{4}"; //07/Mar/2004
	   String regex3="\\d{2}:\\d{2}:\\d{2}"; //16:06:51

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		Pattern p=Pattern.compile(regex3);
		//패턴에 일치하는게 있는지없는지 확인 (있으면 true 없으면 false)
		
		Matcher m=p.matcher(value.toString());
		while(m.find()){
			result.set(m.group());
			//임시저장장소, 한줄읽고 쓰고 한줄읽고 써놓고 누적해놓는곳
			context.write(result, one);
		}
		
		
		
		
	}
}	


































