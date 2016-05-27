package com.reserve.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.util.*;
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
    public static List<MovieInfoVO> movieInfoData()
    {
    	SqlSession session=ssf.openSession();
    	List<MovieInfoVO> list=session.selectList("movieInfoData");
    	session.close();
    	return list;
    }
    public static List<TheaterInfoVO> theaterInfoData()
    {
    	SqlSession session=ssf.openSession();
    	List<TheaterInfoVO> list=session.selectList("theaterInfoData");
    	session.close();
    	return list;
    }
    public static String theaterNumber(int mno)
    {
    	SqlSession session=ssf.openSession();
    	String list=session.selectOne("theaterNumber",mno);
    	session.close();
    	return list;
    }
    public static TheaterInfoVO theaterInfoData(int tno)
    {
    	SqlSession session=ssf.openSession();
    	TheaterInfoVO list=session.selectOne("theaterInfoData",tno);
    	session.close();
    	return list;
    }
    public static String timeInfoData(int tno)
    {
    	SqlSession session=ssf.openSession();
    	String list=session.selectOne("timeInfoData",tno);
    	session.close();
    	return list;
    }
    
    // ¿¹¾à
    public static List<ReserveVO> reserveUserAllData(String id)
    {
    	SqlSession session=ssf.openSession();
    	List<ReserveVO> list=session.selectList("reserveUserAllData",id);
    	session.close();
    	return list;
    }
    public static List<ReserveVO> reserveAdminAllData()
    {
    	SqlSession session=ssf.openSession();
    	List<ReserveVO> list=session.selectList("reserveAdminAllData");
    	session.close();
    	return list;
    }
    public static void reserveInsert(ReserveVO vo)
    {
    	SqlSession session=ssf.openSession(true);
    	session.insert("reserveInsert",vo);
    	session.close();
    }
    public static void reserveOkUpdate(int no)
    {
    	SqlSession session=ssf.openSession(true);
    	session.update("reserveOkUpdate",no);
    	session.close();
    }
}
