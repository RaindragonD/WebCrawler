package store;

import model.ImgModel;
import java.util.List;

public class PreProcess {
	public static List<ImgModel> delRptImg(List<ImgModel> imgs){
		for (int i=0;i<imgs.size();i++) {
			ImgModel img = imgs.get(i);
			String url = img.getImgURL();
			for (int j=i+1;j<imgs.size();j++) {
				ImgModel img_temp = imgs.get(j);
				String url_temp = img_temp.getImgURL();
				if (url_temp.equals(url)){
					imgs.remove(img_temp);
				}
			}
		}
		return imgs;
	}
	
	public static List<ImgModel> modName(List<ImgModel> imgs){ 
		for (int i=0;i<imgs.size();i++) {
			ImgModel img = imgs.get(i);
			String name = img.getImgName();
			if (name.equals(".jpg")) {name="Unnamed.jpg";}
			name = (i+1)+" "+name;
			img.setImgName(name);
		}
		return imgs;
	}
}
