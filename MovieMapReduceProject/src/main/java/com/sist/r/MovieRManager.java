package com.sist.r;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class MovieRManager {
	public void rGraph()
	{
		try
		{
			RConnection rc=new RConnection();
			rc.voidEval("data<-read.table(\"/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/output/part-r-00000\")");//table을 주면 자동으로 title생성
			rc.voidEval("png(\"/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/main/feel.png\",width=900,height=500)");
			rc.voidEval("par(mfrow=c(1,2))"); //그래프를 합칠때 사용 (1줄에 2개)
			rc.voidEval("pie(data$V2,labels=data$V1,col=rainbow(10))");
			rc.voidEval("barplot(data$V2,names.arg=data$V1,col=rainbow(10))"); //names.arg = labels
			rc.voidEval("dev.off()");
			rc.close();
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	// 데이터 받기 => 몽고
	public List<FeelVO> rFeelData()
	{
		List<FeelVO> list=new ArrayList<FeelVO>();
		try
		{
			RConnection rc=new RConnection();
			rc.voidEval("data<-read.table(\"/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/output/part-r-00000\")");
			REXP p=rc.eval("data$V1"); //감성표현 데이터 가져옴
			String[] feel=p.asStrings(); //감성표현 String 받음
			p=rc.eval("data$V2");
			int[] count=p.asIntegers();
			rc.close();
			
			for(int i=0;i<count.length;i++)
			{
				if(count[i]>=3)
				{
					FeelVO vo=new FeelVO();
					vo.setFeel(feel[i]);
					vo.setCount(count[i]);
					list.add(vo);
				}
			}
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return list;
	}
}
