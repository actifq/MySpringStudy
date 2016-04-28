package com.movie.dao;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.rosuda.REngine.Rserve.RConnection;

import java.io.*;
import java.net.*;
public class MovieManager {
   public static void main(String[] arg)
   {
	   MovieManager m=new MovieManager();
	   //m.movieAllData();
	   File file=new File("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WebFinalProject\\images\\desc.txt");
	   if(file.exists())
		   file.delete();
	   for(int i=1;i<=3;i++)
	   {
	    String json=m.review_data("시간이탈자", i);
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
		   //System.out.println(doc);
		   /*
		    *  <div class="box-image" >
                        <strong class="rank">No.2</strong>	
                        <a href="/movies/detail-view/?midx=78746">
                            <span class="thumb-image">
                                <img src="http://img.cgv.co.kr/Movie/Thumbnail/Poster/000078/78746/78746_185.jpg" alt="주토피아 포스터" onerror="errorImage(this)"/>
                                <span class="ico-grade grade-all">전체</span>
                            </span>
                            
                        </a>

		    */
		   Elements titleElem=doc.select("div.box-contents strong.title");
		   Elements imageElem=doc.select("div.box-image a span.thumb-image img");
		   Elements gradeElem=doc.select("div.box-image a span.thumb-image span");
		   Elements rankElem=doc.select("div.box-image strong.rank");
		   /*
		    *  <span class="like"> 
                            <button class="btn-like" value="78746">영화 찜하기</button>
                            <span class="count"> 
                                <strong><i>12,561</i><span>명이 선택</span></strong> 
                                <i class="corner-RT"></i><i class="corner-LT"></i><i class="corner-LB"></i><i class="corner-RB"></i><i class="corner-arrow"></i>
                            </span>
                            <a class="link-reservation" href="/ticket/?MOVIE_CD=20009280&MOVIE_CD_GROUP=20009280">예매</a>

		    */
		   Elements percentElem=doc.select("div.box-contents div.score strong.percent span");
		   Elements likeElem=doc.select("div.box-contents span.like span.count strong i");
		   Elements starElem=doc.select("div.box-contents span.percent");
		   Elements dayElem=doc.select("div.box-contents span.txt-info strong");
		   for(int i=0;i<titleElem.size();i++)
		   {
			   //배트맨 대 슈퍼맨: 저스티스의 시작 81.1% 2016.03.24 개봉 24,348 74% http://img.cgv.co.kr/Movie/Thumbnail/Poster/000078/78391/78391_185.jpg No.1 12세 이상
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
			   d.setNo(i+1);
			   d.setTitle(telem.text());
			   d.setReserve(Double.parseDouble(pelem.text().substring(0,pelem.text().lastIndexOf('%'))));
			   d.setImage(img);
			   d.setLike(Integer.parseInt(lelem.text().replace(",","")));
			   d.setRegdate(delem.text().substring(0, delem.text().indexOf("개봉")));
			   //d.setStar(Integer.parseInt(selem.text().substring(0, selem.text().lastIndexOf('%'))));
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
		   String key="7b429affa32c43e1adf62ad1eebb6928";
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
		     //{name:홍길동}  
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
			   data=data.replace("ㅋ", "");
			   data=data.replace("~", "");
			   data=data.replace("ㅠ", "");
			   data=data.replace("^^", "");
			   data=data.replace("ㅜ", "");
			   data=data.replace(":)", "");
			   data=data.replace("ㅎ", "");
			   data=data.replace("(", "");
			   data=data.replace(")", "");
			   data=data.replace("_", "");
			   data=data.replace(":", "");
			   list.add(data);
			   desc+=data+"\n";
		   }
		   
		   //System.out.println(desc);
		   FileWriter fw=new FileWriter("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WebFinalProject\\images\\desc.txt",true);
		   fw.write(desc);
		   fw.close();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   return list;
   }
   public void wordcloud()
   {
	   try
	   {
		   RConnection rc=new RConnection();
		   rc.voidEval("library(KoNLP)");
		   rc.voidEval("library(wordcloud)");
		   rc.voidEval("png(\"C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WebFinalProject/images/desc.png\",width=450,height=500)");
		   rc.voidEval("data<-readLines(\"C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WebFinalProject/images/desc.txt\")");
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
   }
}





