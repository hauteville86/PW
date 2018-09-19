package pl.karolcyprowski.grafika;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class GrafikaProjektKeyEventDispatcher implements KeyEventDispatcher {
	
	private Controller controller;
	
	public GrafikaProjektKeyEventDispatcher(Controller controller) {
		this.controller = controller;
	}

	public boolean dispatchKeyEvent(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode){
			case KeyEvent.VK_COMMA:
				controller.increaseD();
				break;
			case KeyEvent.VK_M:
				controller.decreaseD();
				break;
			case KeyEvent.VK_J:
				controller.translateX(true);
				break;
			case KeyEvent.VK_K:
				controller.translateX(false);
				break;
			case KeyEvent.VK_Y:
				controller.translateY(true);
				break;
			case KeyEvent.VK_H:
				controller.translateY(false);
				break;
			case KeyEvent.VK_G:
				controller.translateZ(true);
				break;
			case KeyEvent.VK_T:
				controller.translateZ(false);
				break;
			case KeyEvent.VK_B:
				controller.rotateX(true);
				break;
			case KeyEvent.VK_N:
				controller.rotateX(false);
				break;
			case KeyEvent.VK_V:
				controller.rotateY(true);
				break;
			case KeyEvent.VK_C:
				controller.rotateY(false);
				break;
			case KeyEvent.VK_X:
				controller.rotateZ(true);
				break;
			case KeyEvent.VK_Z:
				controller.rotateZ(false);
				break;
			default:
				break;
		}
		controller.repaint();
		return true;
	}

}
