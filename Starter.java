package happy;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Starter {
	
	
	public static void main(String[] args){
		Initializer Ini = new Initializer();
		Ini.readIndex("F:\\lab5\\index.txt");
		Ini.readCluster("F:\\lab5\\cluster.txt");
		Ini.readFavorite("F:\\lab5\\favorite.txt");
		new Start(Ini);
	}
}
