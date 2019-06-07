package money;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import money.BankDTO;

public class BankDAO {
	String url="jdbc:mysql://localhost:3306/bank";
	String user="root";
	String password="1234";
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public ArrayList selectAll() {
		
		ArrayList list = new ArrayList();
		BankDTO dto=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url, user, password);
			
			String sql ="select * from member";
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			//SQL문의 결과가 있으면, 받아서 처리
			
			while(rs.next()) {
				dto = new BankDTO();
				String id = rs.getString(2);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String tel = rs.getString(4);
				
				dto.setId(id);
				dto.setName(name);
				dto.setAge(age);
				dto.setTel(tel);
				
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("DB처리 중 에러발생...");
			System.out.println(e.getMessage());
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				
				System.out.println("자원 해제중 에러 발생!!");
			}
		}
		
		return list;
	}
	
	public BankDTO select(String inputId) {
		BankDTO dto=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1.드라이버 설정 완료");
			
			con = DriverManager.getConnection(url, user, password);
			System.out.println("2.DB연결 완료");
			
			String sql ="select * from member where id =?";
			ps = con.prepareStatement(sql);
			ps.setString(1, inputId);
			System.out.println("3.SQL문 객체화 완료");
			
			rs = ps.executeQuery();
			System.out.println("4.SQL문 전송 완료");
			
			if(rs.next()) {
				dto = new BankDTO();
				String id = rs.getString(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String tel = rs.getString(4);
				dto.setId(id);
				dto.setName(name);
				dto.setAge(age);
				dto.setTel(tel);
			}else {
				System.out.println("검색 결과가 없습니다.");
			}
		} catch (Exception e) {
			System.out.println("DB처리 중 에러발생...");
			System.out.println(e.getMessage());
		}finally{
			
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				
				System.out.println("자원 해제중 에러 발생!!");
			}
		}
		
		return dto;
	}
	public void insert(BankDTO dto) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1.드라이버 설정 OK....");
				
		con = DriverManager.getConnection(url, user, password);
		System.out.println("2.DB연결 OK.....");
		
		String sql="insert into member values(?,?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, dto.getId());
		ps.setString(2, dto.getName());
		ps.setString(3, dto.getAge());
		ps.setString(4, dto.getTel());
		System.out.println("3.SQl문 결정 OK...");
		
		ps.executeUpdate();
		System.out.println("4.SQL문 전송 OK....");
		ps.close();
	}
	public void update(String tel,String id) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1.드라이버 설정 OK....");
						
		con = DriverManager.getConnection(url, user, password);
		System.out.println("2.DB연결 OK.....");
		
		String sql="update member set tel=? where id=?";
		ps = con.prepareStatement(sql);
		ps.setString(1, tel);
		ps.setString(2, id);
		System.out.println("3.SQl문 결정 OK...");
		
		ps.executeUpdate();
		System.out.println("4.SQL문 전송 OK....");
		ps.close();
	}
	public void delete(String id) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1.드라이버 설정 OK....");
			
		con = DriverManager.getConnection(url, user, password);
		System.out.println("2.DB연결 OK.....");
		
		String sql="delete from member where id = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, id);
		System.out.println("3.SQl문 결정 OK...");
		
		ps.executeUpdate();
		System.out.println("4.SQL문 전송 OK....");
		ps.close();
	}

}
