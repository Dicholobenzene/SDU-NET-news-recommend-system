package happy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public class Initializer {
	HashMap<String,String> index = new HashMap<String,String>();
	HashMap<String,String> cluster = new HashMap<String,String>();
	int favorite[] = new int[40];
	String favoritepath;
	//after initialize,in 2 hashmap,there are <ID,fullname> and <ID,family>
	
	void readIndex(String path){
		FileReader fr = null;
		BufferedReader br = null;
		try{
			try{
				fr = new FileReader(path);
			}catch(Exception e){
				e.printStackTrace();
			}
			br = new BufferedReader(fr);
			String word,number,title ;
			while((word = br.readLine())!=null){
				if(word.length()>0){
				number = word.substring(0, word.indexOf(" "));
				word=word.substring(word.indexOf(" ")+1,word.length());
				word=word.substring(word.indexOf("    ")+3,word.length());
				title = word;
				index.putIfAbsent(number, title);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void readCluster(String path){
		FileReader fr = null;
		BufferedReader br = null;
		try{
			try{
				fr = new FileReader(path);
			}catch(Exception e){
				e.printStackTrace();
			}
			br = new BufferedReader(fr);
			String word,number,title ;
			while((word = br.readLine())!=null){
				title = word.substring(0, word.indexOf(":"));
				word=word.substring(word.indexOf(":")+1,word.length());
				while(word.contains(" ")){
					number = word.substring(0, word.indexOf(" "));
					word=word.substring(word.indexOf(" ")+1,word.length());
					cluster.putIfAbsent(number, title);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void readFavorite(String path){
		favoritepath = path;
		for(int i=0;i<40;i++)
			favorite[i]=0;
		FileReader fr = null;
		BufferedReader br = null;
		try{
			try{
				fr = new FileReader(path);
			}catch(Exception e){
				e.printStackTrace();
			}
			br = new BufferedReader(fr);
			String word;
			int num;
			while((word = br.readLine())!=null){
				if(word.length()>0){
				num = Integer.parseInt(word);
				favorite[num]++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	void addFavortie(int num){
		String family=cluster.get(Integer.toString(num));
		int Family = Integer.parseInt(family);
		favorite[Family]++;
		try {
			FileWriter fw = new FileWriter(favoritepath,true);
			fw.write(family+"\r\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void RefreshFavorite(){
		for(int i=0;i<40;i++)
			favorite[i]=0;
		try {
			FileWriter fw = new FileWriter(favoritepath,false);
			fw.write("");
			fw.close();
			}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
