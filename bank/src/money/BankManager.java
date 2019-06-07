package money;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class BankManager {
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	
	public BankManager() {
		JFrame f = new JFrame("은행 고객 정보 확인");
		BankDAO dao = new BankDAO();
		BankDTO dto = new BankDTO();
		
		f.setSize(1000,800);
		f.getContentPane().setLayout(null);
		
		t1 = new JTextField();
		t1.setFont(new Font("굴림", Font.PLAIN, 16));
		t1.setBounds(126, 153, 221, 35);
		f.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setFont(new Font("굴림", Font.PLAIN, 16));
		t2.setColumns(10);
		t2.setBounds(126, 198, 221, 35);
		f.getContentPane().add(t2);
		
		t3 = new JTextField();
		t3.setFont(new Font("굴림", Font.PLAIN, 16));
		t3.setColumns(10);
		t3.setBounds(125, 243, 222, 35);
		f.getContentPane().add(t3);
		
		t4 = new JTextField();
		t4.setFont(new Font("굴림", Font.PLAIN, 16));
		t4.setColumns(10);
		t4.setBounds(126, 296, 221, 35);
		f.getContentPane().add(t4);
		
		JTextArea ta = new JTextArea();
		ta.setFont(new Font("Monospaced", Font.PLAIN, 20));
		ta.setBounds(397, 138, 541, 422);
		f.getContentPane().add(ta);
		
		JButton btnNewButton = new JButton("신규 고객 등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dto.setId(t1.getText());
				dto.setName(t2.getText());
				dto.setAge(t3.getText());
				dto.setTel(t4.getText());
				
				try {
					dao.insert(dto);
					ta.setText("회원가입 성공\n"
							+ "아이디: "+dto.getId()+"\n"
							+ "이름: "+dto.getName()+"\n"
							+ "나이: "+dto.getAge()+"\n"
							+ "전화번호: "+dto.getTel());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(53, 398, 142, 51);
		f.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("고객 정보 수정");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dto.setTel(t4.getText());
				dto.setId(t1.getText());
				String tel = dto.getTel();
				String id = dto.getId();
				try {
					dao.update(tel, id);
					ta.setText(id+"님의 전화번호가 "+tel+"(으)로 바뀌었습니다.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(207, 398, 143, 51);
		f.getContentPane().add(button);
		
		JButton button_1 = new JButton("고객 정보 삭제");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dto.setId(t1.getText());
				String id = dto.getId();
				try {
					dao.delete(id);
					ta.setText(id+"님의 정보가 사라졌습니다.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(53, 459, 142, 51);
		f.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("고객 정보 검색");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankDTO dto = new BankDTO();
				dto.setId(t1.getText());
				String id = dto.getId();
				try {
					dto=dao.select(id);
					ta.setText(id+"님의 정보 입니다.\n"
							+ "아이디: "+dto.getId()+"\n"
							+ "이름: "+dto.getName()+"\n"
							+ "나이: "+dto.getAge()+"\n"
							+ "전화번호: "+dto.getTel());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(207, 459, 143, 51);
		f.getContentPane().add(button_2);
		
		JButton btnNewButton_1 = new JButton("전체 출력");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BankDTO dto = new BankDTO();
				ArrayList list = new ArrayList();
				try {
					String id="";
					String name="";
					String age="";
					String tel="";
					list=dao.selectAll();
					for (int i = 0; i < list.size(); i++) {
						dto = (BankDTO)list.get(i);
						id += dto.getId()+" ";
						name += dto.getName()+" ";
						age += dto.getAge()+" ";
						tel += dto.getTel()+" ";
					}
					ta.setText("OO은행 전체 정보\n"
							+ "아이디: "+id+"\n"
							+ "이름: "+name+"\n"
							+ "나이: "+age+"\n"
							+ "전화번호: "+tel);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(138, 527, 130, 50);
		f.getContentPane().add(btnNewButton_1);

		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(34, 153, 80, 35);
		f.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("이름");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.PLAIN, 18));
		label.setBounds(34, 198, 80, 35);
		f.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("나이");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("굴림", Font.PLAIN, 18));
		label_1.setBounds(34, 243, 80, 35);
		f.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("전화번호");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("굴림", Font.PLAIN, 18));
		label_2.setBounds(34, 296, 80, 35);
		f.getContentPane().add(label_2);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(918, 138, 17, 422);
		f.getContentPane().add(scrollBar);
		
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		BankManager bank = new BankManager();
	}
}
