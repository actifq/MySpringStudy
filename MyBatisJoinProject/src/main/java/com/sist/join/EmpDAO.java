package com.sist.join;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.util.*;

public class EmpDAO {
	private static SqlSessionFactory ssf;
    static
    {
    	try
    	{	
    		System.out.println(1);
    		Reader reader=Resources.getResourceAsReader("Config.xml");
    		ssf=new SqlSessionFactoryBuilder().build(reader);
    		// SAX파싱 
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
    public static List<EmpVO> empdeptJoinAllData()
    {
    	System.out.println(2);
    	
    	SqlSession session=ssf.openSession();
    	
    	
    	System.out.println(3);
    	
    	
    	List<EmpVO> list=session.selectList("empdeptJoinAllData");
    	session.close();// 반환
    	return list;
    }
    public static EmpVO empdeptJoinFindData(int empno)
    {
    	SqlSession session=ssf.openSession();
    	EmpVO list=session.selectOne("empdeptJoinFindData",empno);
    	session.close();// 반환
    	return list;
    }
}



