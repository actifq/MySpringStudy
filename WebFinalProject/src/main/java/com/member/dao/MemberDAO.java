package com.member.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	private static SqlSessionFactory ssf;

	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static String memberLogin(String id, String pwd) {
		String result = "";
		SqlSession session = ssf.openSession();
		int count = session.selectOne("memberIdCount", id);
		if (count == 0) {
			result = "NOID";
		} else {
			MemberDTO d = session.selectOne("memberGetPwd", id);
			if (pwd.equals(d.getPwd())) {
				result = d.getName() + "|" + d.getAdmin();
			} else {
				result = "NOPWD";
			}
		}
		session.close();
		return result;
	}
	
	public static int memberIdCheck(String id){
		SqlSession session=ssf.openSession();
		int count=session.selectOne("memberIdCount",id);
		session.close();
		return count;
	}
}





















