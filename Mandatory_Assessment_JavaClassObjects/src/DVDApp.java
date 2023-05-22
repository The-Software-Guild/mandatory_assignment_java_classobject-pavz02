import com.controller.DVDController;
import com.dao.daoDVDImpl;
import com.view.DVDView;

public class DVDApp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DVDController controller = new DVDController(new DVDView(), new daoDVDImpl());
		controller.start();
	}
}
