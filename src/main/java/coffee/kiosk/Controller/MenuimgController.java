package coffee.kiosk.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import coffee.kiosk.model.Menuimg;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MenuimgController {
    @FXML
    private ImageView img;

    @FXML
    private Label name;

    @FXML
    private Label price;

    public void setData(Menuimg menuimg){
        try {
            // 상대 경로를 설정
            String relativePath = String.valueOf(menuimg.getFood_img());

            // 클래스 로더를 사용하여 리소스 로드
            InputStream imageStream = getClass().getClassLoader().getResourceAsStream(relativePath);
            if (imageStream == null) {
                throw new FileNotFoundException("File not found: " + relativePath);
            }

            Image image = new Image(imageStream);
            img.setImage(image);
            name.setText(menuimg.getFood_name());
            price.setText(String.valueOf(menuimg.getFood_price()));

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
