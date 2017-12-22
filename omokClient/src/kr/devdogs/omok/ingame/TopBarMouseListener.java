 package kr.devdogs.omok.ingame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import kr.devdogs.omok.net.Client;

public class TopBarMouseListener implements MouseListener, MouseMotionListener{
	private TopDesign topBar;
	public TopBarMouseListener(TopDesign topBar) {
		this.topBar = topBar;
	}

	//	클릭한 상태로 움직이는 경우 돌 미리보기가 따라오지 않는 경우를 방지하기 위해 드래그에도 추가.
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Available(e.getX(), e.getY()))
			effect2();
		else
			effect3();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Available(e.getX(), e.getY()))
			effect2();
		else
			effect3();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	//	 클릭시에 돌을 놓는 경우 마우스가 조금만 움직이고 있어도 drag로 해석되어버리는 경우가 많아서 실행이 되지 않는 버그가 발생하기 때문에 마우스를 뗄 때 실행되도록 함.
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Available(e.getX(), e.getY()))
			effect();
	}
	
	private boolean Available(int x, int y) {
		return ((1133<=x&&x<=1250)&&(18<=y&&y<=126)) ? true:false;
	}
	
	//	유효 영역 클릭시 발생하는 이벤트
	private void effect() {
		String pocket = 110 + "/" + 0 + "/" + 0 + "/" + 0 + "/" + 0;
		Client.getInstance().dataSend(pocket);
	}
	
	//	유효 영역에 마우스를 올려놓은 경우 실행.
	private void effect2() {
		this.topBar.mouseOn = true;
	}
	
	//	유효 영역에서 마우스가 빠져나간 경우 실행.
	private void effect3() {
		this.topBar.mouseOn = false;
	}
}