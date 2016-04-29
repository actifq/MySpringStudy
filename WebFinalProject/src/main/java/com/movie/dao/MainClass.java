package com.movie.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

import java.io.*;
public class MainClass {
	static Komoran komoran=new Komoran("C:\\test2");
	static List<Word> list=new ArrayList<Word>();
	public static void main(String[] args) throws Exception{
//		List<List<Pair<String,String>>> result=komoran.analyze("나는 바람과 함께 사라지다를 보았는데, 기분이 우울해진거 있죠? 글쎄, 거기 주인공이 사실 귀신이라나 뭐라나."
//				+ "주인공만 . 아무튼 영화는 재밌으니깐 추천은 드려요. 결론은 바람과 함께 사라지다는 약간 신비스럽고 몽환적임.주인공 짱짱");
		File file = new File("c:\\test2\\jeju.txt");
		FileReader fr = new FileReader(file);
//		BufferedReader br = new BufferedReader(fr);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"EUC-KR"));

		String str = "";
		while((str = br.readLine()) != null) {
			komo(str);
		}
		
		String s1 = "";
		int ttt = 1;
		for(int i=0; i<list.size(); i++){
			for(int j=i; j<list.size(); j++){
				if(list.get(j).getCount()>list.get(i).getCount()){
					s1 = list.get(i).getWord();
					ttt = list.get(i).getCount();
					list.get(i).setWord(list.get(j).getWord()); 
					list.get(i).setCount(list.get(j).getCount());
					list.get(j).setWord(s1);
					list.get(j).setCount(ttt);
				}
			}
		}
		
//		for(Word tmp:list){
//			System.out.println(tmp.getWord()+" "+tmp.getCount());
//		}
		String[] feel = {"사랑","로맨스","매력","즐거움","스릴",
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
		for(Word tmp:list){
			for(int k = 0; k<feel.length; k++){
				if(feel[k].equals(tmp.getWord()))
					System.out.println(tmp.getWord()+" "+tmp.getCount());
			}
//			System.out.println(tmp.getWord()+" "+tmp.getCount());
		}
	}
	public static void komo(String s){
		List<List<Pair<String,String>>> result=komoran.analyze(s);
		
		for (List<Pair<String, String>> eojeolResult : result) {
			for (Pair<String, String> wordMorph : eojeolResult) {
				if(wordMorph.getSecond().equals("NNG")||wordMorph.getSecond().equals("NNP")){
					boolean ch=true;
					for(Word tmp:list){
						if(tmp.getWord().equals(wordMorph.getFirst())){
							int count=tmp.getCount()+1;
							tmp.setCount(count);
							ch=false;
							break;
						}
					}
					if(ch){
						Word a=new Word();
						a.setWord(wordMorph.getFirst());
						a.Incerement();
						list.add(a);
					}
				}
			}
		}
	}
}

