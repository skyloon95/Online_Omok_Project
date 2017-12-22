package kr.devdogs.omok.login;
import java.util.ArrayList;
import java.util.Arrays;

public class Member{
	private String id;
	private String passwordR;
	private String  nickName;
	private  String  passwordRe;
	ArrayList<String> idSave=new ArrayList<>();
	ArrayList<String> passwordRSave=new ArrayList<>();
	ArrayList<String> nickNameSave=new ArrayList<>();

	
	


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswordR() {
		return passwordR;
	}
	public void setPasswordR(String cs) {
		this.passwordR = cs;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPasswordRe() {
		return passwordRe;
	}
	public void setPasswordRe(String cs) {
		this.passwordRe = cs;
	}
	public void saveId() {
		idSave.add(id);
	}
	public void savePasswordR() {
		passwordRSave.add(passwordR);
	}
	public void saveNickName() {
		nickNameSave.add(nickName);
	}
	public boolean passwordCheck()
	{
		boolean check=true;
		for(int i=0; i<passwordRSave.size();i++)
		{
			if(passwordR==passwordRSave.get(i) && passwordRe==passwordRSave.get(i) && passwordR!=passwordRe)
			{
				check=false;
			}
		}
		return check;
	}
	public boolean nickNameCheck()
	{
		boolean check=true;
		for(int i=0;i<nickNameSave.size();i++)
		{
			if(nickName==nickNameSave.get(i))
			{
				check=false;
			}
		}
		return check;
	}

	public  boolean idCheck()
	{
		boolean check=true;
		for(int i=0;i<idSave.size();i++)
		{
			if(id==idSave.get(i))
			{
				check=false;
			}
		}
		return check;
	}
	@Override
	public String toString()
	{
		return id+" "+passwordR+" "+passwordRe+" "+nickName;
		
	}



	


}
