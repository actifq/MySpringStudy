package com.sist.data;
import java.util.*;

/*
 * String[] feel = {"사랑","로맨스","매력","즐거움","스릴",
				"소름","긴장","공포","유머","웃음","개그",
				"행복","전율","경이","우울","절망","신비",
				"여운","희망","긴박","감동","감성","휴머니즘",
				"자극","재미","액션","반전","비극","미스테리",
				"판타지","꿈","설레임","흥미","풍경","일상",
				"순수","힐링","눈물","그리움","호러","충격","잔혹"};
		String[] genre = {
				"드라마","판타지","공포","멜로","애정",
				"로맨스","모험","스릴러","느와르","다큐멘터리",
				"코미디","미스터리","범죄","SF","액션","애니메이션"	
		};
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
@Component
public class MovieManager {
   public static void main(String[] arg)
   {
      MovieManager m=new MovieManager();
      //m.movieAllData();
      File file=new File("/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/desc.txt");
      if(file.exists())
         file.delete();
      for(int i=1;i<=3;i++)
      {
       String json=m.review_data("", i);
       //System.out.println(json);
       m.jsonParse(json);
      }
   }
   public List<MovieDTO> movieAllData()
   {
      List<MovieDTO> list=new ArrayList<MovieDTO>();
      try
      {
         Document doc=Jsoup.connect("http://www.cgv.co.kr/movies").get();
        
         Elements titleElem=doc.select("div.box-contents strong.title");
         Elements imageElem=doc.select("div.box-image a span.thumb-image img");
         Elements gradeElem=doc.select("div.box-image a span.thumb-image span");
         Elements rankElem=doc.select("div.box-image strong.rank");
        
         Elements percentElem=doc.select("div.box-contents div.score strong.percent span");
         Elements likeElem=doc.select("div.box-contents span.like span.count strong i");
         Elements starElem=doc.select("div.box-contents span.percent");
         Elements dayElem=doc.select("div.box-contents span.txt-info strong");
         String[] color={"b87333","silver","gold","e5e4e2","#ccffcc","#ccccff","#ededed"};
         for(int i=0;i<titleElem.size();i++)
         {
            
            Element telem=titleElem.get(i);
            Element pelem=percentElem.get(i);
            Element delem=dayElem.get(i);
            Element lelem=likeElem.get(i);
            Element selem=starElem.get(i);
            Element ielem=imageElem.get(i);
            String img=ielem.attr("src");
            Element relem=rankElem.get(i);
            Element gelem=gradeElem.get(i);
            /*System.out.println(telem.text()
                  +" "+pelem.text()+" "+delem.text()
                  +" "+lelem.text()+" "+selem.text()
                  +" "+img+" "+relem.text()
                  +" "+gelem.text()
                  );*/
            MovieDTO d=new MovieDTO();
            d.setColor(color[i]);
            d.setNo(i+1);
            d.setTitle(telem.text());
            d.setReserve(Double.parseDouble(pelem.text().substring(0,pelem.text().lastIndexOf('%'))));
            d.setImage(img);
            d.setLike(Integer.parseInt(lelem.text().replace(",","")));
            d.setRegdate(delem.text().substring(0, delem.text().indexOf("개봉")));
            d.setStar(Integer.parseInt(selem.text().substring(0, selem.text().lastIndexOf('%'))));
             d.setRank(Integer.parseInt(relem.text().substring(3)));
             d.setGrade(gelem.text());
             list.add(d);
         }
      }catch(Exception ex)
      {
         System.out.println(ex.getMessage());
      }
      return list;
   }
   public MovieDTO movieDetail(int no)
   {
      MovieDTO d=new MovieDTO();
      List<MovieDTO> list=movieAllData();
      d=list.get(no-1);
      return d;
   }
   public MovieDTO movieDetail(String title)
   {
      MovieDTO d=new MovieDTO();
      List<MovieDTO> list=movieAllData();
      for(MovieDTO dd:list)
      {
    	  if(title.equals(dd.getTitle()))
    	  {
    		  d=dd;
    		  break;
    	  }
      }
      return d;
   }
   public List<String> movieRank()
   {
      List<String> list=
            new ArrayList<String>();
      try
      {
         Document doc=Jsoup.connect("http://movie.naver.com/movie/sdb/rank/rmovie.nhn").get();
         Elements elems=doc.select("td.title div.tit3");
         for(int i=0;i<10;i++)
         {
            Element elem=elems.get(i);
            list.add(elem.text());
         }
      }catch(Exception ex)
      {
         System.out.println(ex.getMessage());
      }
      return list;
   }
   public List<String> movieReserve()
   {
      List<String> list=
            new ArrayList<String>();
      try
      {
         Document doc=Jsoup.connect("http://movie.naver.com/movie/sdb/rank/rreserve.nhn").get();
         Elements elems=doc.select("td.title div.tit4");
         for(int i=0;i<10;i++)
         {
            Element elem=elems.get(i);
            list.add(elem.text());
         }
      }catch(Exception ex)
      {
         System.out.println(ex.getMessage());
      }
      return list;
   }
   public List<String> movieBoxoffice()
   {
      List<String> list=
            new ArrayList<String>();
      try
      {
         Document doc=Jsoup.connect("http://movie.naver.com/movie/sdb/rank/rboxoffice.nhn").get();
         Elements elems=doc.select("td.title div.tit1");
         for(int i=0;i<10;i++)
         {
            Element elem=elems.get(i);
            list.add(elem.text());
         }
      }catch(Exception ex)
      {
         System.out.println(ex.getMessage());
      }
      return list;
   }
   // C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\WebFinalProject\images
   // 7b429affa32c43e1adf62ad1eebb6928
   public String review_data(String title,int page)
   {
      StringBuffer sb=new StringBuffer();
      try
      {
         String key="20fc44e9e0601a05998b82b60594437b";
         URL url=new URL("https://apis.daum.net/search/blog?apikey="+key
               +"&result=20&output=json&q="+URLEncoder.encode(title, "UTF-8")
               +"&pageno="+page);
         HttpURLConnection conn=
               (HttpURLConnection)url.openConnection();
         String data="";
         if(conn!=null)
         {
            BufferedReader in=
                 new BufferedReader(
                       new InputStreamReader(
                             conn.getInputStream(), "UTF-8"));
            while(true)
            {
               data=in.readLine();
               if(data==null)
                  break;
               sb.append(data+"\n");
            }
         }
      }catch(Exception ex)
      {
         System.out.println(ex.getMessage());
      }
      return sb.toString();
   }
   // json,xml,txt,csv {key:value},{},{}
   public List<String> jsonParse(String json)
   {
	   List<String> list=new ArrayList<String>();
      try
      {
         JSONParser jp=new JSONParser();
         JSONObject jo=(JSONObject)jp.parse(json);
          //{} JSONObject   [] JSONArray
          // {channel:{item:[{},{},{},{},{}]}
         /*
          *   <channel>
          *     <item>
          *       <a></a>
          *       <b></b>
          *     </item>
          *        <a></a>
          *        <b></b>
          *     <item>
          *     <item>
          *   </channel>
          *   {channel:{item:[{a:,b:},{a:,b:},{a:,b:}]}}
          */
         JSONObject channel=(JSONObject)jo.get("channel");
         JSONArray item=(JSONArray)channel.get("item");
         String desc="";
         for(int i=0;i<item.size();i++)
         {
            JSONObject obj=(JSONObject)item.get(i);
            String data=(String)obj.get("description");
            data=data.replaceAll("[A-Za-z0-9]", "");
            data=data.replace("&", "");
            data=data.replace("/", "");
            data=data.replace(";", "");
            data=data.replace("#", "");
            data=data.replace(".", "");
            data=data.replace("+", "");
            data=data.replace("?", "");
            data=data.replace("!", "");
            data=data.replace(",", "");
            data=data.replace("*", "");
            data=data.replace("~", "");
            data=data.replace("^^", "");
            data=data.replace(":)", "");
            data=data.replace("(", "");
            data=data.replace(")", "");
            data=data.replace("_", "");
            data=data.replace(":", "");
            list.add(data);
            desc+=data+"\n";
         }
       
         //System.out.println(desc);
         FileWriter fw=new FileWriter("/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/desc.txt",true);
         fw.write(desc);
         fw.close();
      }catch(Exception ex)
      {
         System.out.println(ex.getMessage());
      }
      return list;
   }
   /*public void wordcloud()
   {
      try
      {
         RConnection rc=new RConnection();
         rc.voidEval("library(KoNLP)");
         rc.voidEval("library(wordcloud)");
         rc.voidEval("png(\"/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/desc.png\",width=450,height=500)");
         rc.voidEval("review<-sapply(data,extractNoun,USE.NAMES=F)");
         rc.voidEval("word<-table(unlist(review))");
         rc.voidEval("wordcloud(names(word),freq = word,"
                  + "scale = c(8,1),"
                  + "rot.per = 0.25,"
               +"min.freq = 1,random.order = F,"
               + "colors=rainbow(15))");
         rc.voidEval("dev.off()");
         rc.close();
      }catch(Exception ex)
      {
         System.out.println(ex.getMessage());
      }
   }*/
}







