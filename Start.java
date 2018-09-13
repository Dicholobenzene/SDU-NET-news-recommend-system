package happy;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

	public class Start extends JFrame{
		Initializer info;
		JPanel b;
		int mode =0;//0-弱推荐模式 1-强推荐模式
		public class link extends JButton{
			int num;
			public link(int number){
				num = number;
				setOpaque(false);
				setBackground(new Color(0,0,255));
				setBorder(new EmptyBorder(0,0,0,0));
				setFont(new Font("微软雅黑",Font.BOLD,37));
				setHorizontalAlignment(JTextField.CENTER);
				this.setText(info.index.get(Integer.toString(num)));
				this.addActionListener(new clickListener());
			}
			class clickListener implements ActionListener{

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					info.addFavortie(num);
					try {
						Runtime.getRuntime().exec("NotePad.exe "+"F:\\lab5\\"+num+".txt");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					refresh();
				}}
		}
		
		int[] files=new int[6];
		
		
		void getFiles(){
			double total=0;
			for(int i=0;i<40;i++)
				total+=info.favorite[i];
			double basePosibility = 0.1;//声明完全无关的项目被选中的机会
			double Choose = 0.5;//超过这个数即被选中
			int chosen=0;
			while(true){
			for(int i=1;i<820;i++){
				if(Math.random()<0.2){

					double p;
					try{
					p=(basePosibility*(1+(double)info.favorite[Integer.parseInt(info.cluster.get(Integer.toString(i)))]))/total;//p∈[0.1,1,1]
					}catch(Exception e){
						p=Math.random();
					}
					p=p*Math.random()*6+0.1;
					switch(mode){
					case 0:
						if(p>Choose){
						files[chosen]=i;
						chosen++;
						if(chosen==6)
							return;
						}
						break;
					case 1:
						if(info.favorite[Integer.parseInt(info.cluster.get(Integer.toString(i)))]>=1){
							if(p>Choose){
								files[chosen]=i;
								chosen++;
								if(chosen==6)
									return;
								}
						}
						break;
					}
					}
				}
			}
		}
		
		void addButton(){
			JButton b[]= new JButton[6];
			for(int i=0;i<6;i++){
				b[i] =new link(files[i]);
				b[i].setBounds(295, 165+111*i, 960, 100);
				this.b.add(b[i]);
			}
			JButton c=new JButton();
			c.setBounds(475, 80, 200, 50);
			c.setOpaque(false);
			c.setBackground(new Color(0,0,255));
			c.setBorderPainted(false);
			c.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mode = (mode+1)%2;
					try{
						refresh();
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "当前没有偏好信息，无法切换至强推荐模式！", "失败", JOptionPane.PLAIN_MESSAGE);
						e1.printStackTrace();
						return;

					}
				}});
			this.b.add(c);
			JButton d=new JButton();
			d.setBounds(733, 80, 200, 50);
			d.setOpaque(false);
			d.setBackground(new Color(0,0,255));
			d.setBorderPainted(false);
			d.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					info.RefreshFavorite();
					mode=0;
					refresh();
				}});
			this.b.add(d);
			JTextField mmo =new JTextField();
			mmo.setBounds(0, 0, 400, 100);
			mmo.setOpaque(false);
			mmo.setBackground(new Color(0,0,255));
			mmo.setBorder(new EmptyBorder(0,0,0,0));
			mmo.setFont(new Font("萝莉体",Font.BOLD,17));
			mmo.setHorizontalAlignment(JTextField.CENTER);
			String res="";
			if(mode==0)
				res+="弱推荐模式";
			else
				res+="强推荐模式";
			for(int i=0;i<6;i++)
				res+="<"+files[i]+","+info.cluster.get(Integer.toString(files[i]))+">";
			mmo.setText(res);
			this.b.add(mmo);
			this.b.repaint();
		}
		
		void refresh(){
			b.removeAll();
			getFiles();
			addButton();
			b.repaint();
		}
		
		
		
		public Start(Initializer a){
			ImageIcon background;
			JPanel image;
			info = a;
			//
			background=new ImageIcon("F:/Lab5/background.png");
			JLabel label= new JLabel(background);
			label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
			image=(JPanel)getContentPane();
			getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
			image.setOpaque(false);
			image.setLayout(null);		
			getLayeredPane().setLayout(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(background.getIconWidth()+5, background.getIconHeight()+20);
			setLocation(260, 100);
			setResizable(false);
			setVisible(true);
			setTitle("推荐系统");
			b=image;
			
			
			getFiles();
			addButton();
			
			
		}
		
		
	}

