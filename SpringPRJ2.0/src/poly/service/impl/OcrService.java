package poly.service.impl;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import poly.dto.OcrDTO;
import poly.service.IOcrService;
import poly.util.CmmUtil;
@Service("OcrService")
public class OcrService implements IOcrService{
	private Logger log = Logger.getLogger(this.getClass());
	@Override
	public OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception {
		// TODO Auto-generated method stub
		log.info(this.getClass().getName() + ".getFoodInfoFromWEB start!");
		File imageFile = new File(CmmUtil.nvl(pDTO.getFilePath())+"//"+CmmUtil.nvl(pDTO.getFileName()));
		ITesseract instance = new Tesseract();
		instance.setDatapath("C:\\tess-data");
		instance.setLanguage("eng");
		String result = instance.doOCR(imageFile);
		pDTO.setTextFromImage(result);
		log.info("result :" + result);
		log.info(this.getClass().getName() + ".getFoodInfoFromWEB start!");
		return pDTO;
	}

}
