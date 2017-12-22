package kr.devdogs.omok.login.listener;

import kr.devdogs.omok.login.Login;

public interface MemberEventListener {
	public void onLoginResult(String nickname);
	public void onJoinResult(String responseCode);
	public void onCheckIdResult(String responseCode);
	public void onCheckNicknameResult(String responseCode);
}
