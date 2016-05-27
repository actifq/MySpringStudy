package com.sist.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.*;

import com.sist.data.MovieDTO;
import com.sist.data.MovieManager;
import com.sist.mapred.MovieDriver;
import com.sist.r.FeelVO;
import com.sist.r.MovieRManager;
import com.sist.mongo.*;

@Controller
public class MovieController {
	@Autowired
	private MovieManager mgr;
	@Autowired
	private MovieDriver md;
	@Autowired
	private MovieRManager mr;
	@Autowired
	private MovieDAO dao;
	
	@RequestMapping("main/list.do")
	public String movie_list(Model model)
	{
		List<MovieDTO> list=mgr.movieAllData();
		List<String> raList=mgr.movieRank();
		List<String> reList=mgr.movieReserve();
		List<String> bList=mgr.movieBoxoffice();
		model.addAttribute("list",list);
		model.addAttribute("raList",raList);
		model.addAttribute("reList",reList);
		model.addAttribute("bList",bList);
		return "main/list";
	}
	@RequestMapping("main/detail.do")
	public String movie_detail(int no,Model model)
	{
		File file=new File("/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/desc.txt");
	     if(file.exists())
	        file.delete();
	     
	     /*file=new File("/home/sist/javaStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MovieMapReduceProject/desc.txt");
	     file.createNewFile(); 생성이 안될시에는 내가 생성해줘야함*/
		MovieDTO vo=mgr.movieDetail(no);
		//댓글 수집
		for(int i=1;i<=3;i++)
		{
			String json=mgr.review_data(vo.getTitle(), i);
			mgr.jsonParse(json);
			
		}
		//MapReduce(Hadoop) => part-r-00000
		md.movieMapReduce();
		//Rserve => part-r-00000 (Graph)
		mr.rGraph();
		//Rserve => Data받기
		List<FeelVO> flist=mr.rFeelData();
		//필요한 데이터를 MongoDB에 저장 (추천)
		for(FeelVO r:flist)
		{
			MovieVO mv=new MovieVO();
			mv.setTitle(vo.getTitle());
			mv.setFeel(r.getFeel());
			mv.setCount(r.getCount());
			dao.recommandInsert(mv);
		}
		model.addAttribute("vo",vo);
		return "main/detail";
	}
	@RequestMapping("main/recommand.do")
	public String main_recommand(String feel,Model model)
	{
		if(feel==null)
			feel="재미";
		List<String> flist=dao.recommandFeelData();
		List<String> mlist=dao.recommandTitleData(feel);
		List<MovieDTO> list=new ArrayList<MovieDTO>();
		for(String title:mlist)
		{
			MovieDTO d=mgr.movieDetail(title);
			list.add(d);
		}
		
		List<MovieVO> mflist=dao.recommandMovieFeelData(feel);
		
		model.addAttribute("flist",flist);
		model.addAttribute("list",list);
		model.addAttribute("feel",feel);
		model.addAttribute("mflist",mflist);
		return "main/recommand";
	}
	@RequestMapping("main/total.do")
	public String main_total(Model model)
	{
		List<MovieDTO> list=mgr.movieAllData();
		/*
		 *  [
                    ['Firefox', 45.0],
                    ['IE', 26.8],
                    {
                        name: 'Chrome',
                        y: 12.8,
                        sliced: true,
                        selected: true
                    },
                    ['Safari', 8.5],
                    ['Opera', 6.2],
                    ['Others', 0.7]
                ]
		 */
		String data="[";
		int i=0;
		for(MovieDTO d:list)
		{
			if(i==2)
			{
				data+="{"
                      +"name: '"+d.getTitle()+"',"
                      +"y: "+d.getReserve()+","
                      +"sliced: true,"
                      +"selected: true"
                      +"},";
			}
			else
			{
				data+="['"+d.getTitle()+"',"+d.getReserve()+"],";
			}
			i++;
		}
				data=data.substring(0,data.lastIndexOf(','));
		data+="]";
		model.addAttribute("data",data);
		model.addAttribute("list",list);
		return "main/total";
	}
}






