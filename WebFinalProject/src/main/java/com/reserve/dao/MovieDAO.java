package com.reserve.dao;

import java.io.Reader;
import java.util.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MovieDAO {

	private static SqlSessionFactory ssf;
    static
    {
    	try
    	{
    		Reader reader=Resources.getResourceAsReader("Config.xml");
    		ssf=new SqlSessionFactoryBuilder().build(reader);
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
    
    public static List<MovieInfoVO> movieInfoData(){
    	SqlSession session=ssf.openSession();
    	List<MovieInfoVO> list=session.selectList("movieInfoData");
    	session.close();
    	return list;
    }
    
    public static List<TheaterInfoVO> theaterInfoData(){
    	SqlSession session=ssf.openSession();
    	List<TheaterInfoVO> list=session.selectList("theaterInfoData");
    	session.close();
    	return list;
    }
    
    
    
	
}
