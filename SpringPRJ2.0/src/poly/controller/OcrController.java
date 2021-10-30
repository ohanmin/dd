package poly.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.dto.OcrDTO;
import poly.service.IOcrService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
import poly.util.FileUtil;

@Controller
public class OcrController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource(name = "OcrService")
	private IOcrService ocrService;
	final private String FILE_UPLOAD_SAVE_PATH = "c:/upload";
	@RequestMapping(value = "ocr/imageFileUpload")
	public String Index() {
		log.info(this.getClass() + ".imageFileUpload");
		return "/ocr/imageFileUpload";
	}
	@RequestMapping(value="ocr/getReadforImageText")
	public String getReadforImageText(HttpServletRequest req, HttpServletResponse resp,
			ModelMap model, @RequestParam(value= "fileUpload") MultipartFile mf) throws Exception {
		log.info(this.getClass() + ".getReadforImageText start!");
		String res = "";
		String originalFileName = mf.getOriginalFilename();
		String ext = originalFileName.substring(originalFileName.lastIndexOf(".") +1,originalFileName.length()).toLowerCase();
		if(ext.equals("jpeg") || ext.equals("jpg")|| ext.equals("gif")||ext.equals("png")) {
			String saveFileName = DateUtil.getDateTime("24hhmmss")+ "."+ ext;
			String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);
			String fullFileInfo = saveFilePath + "/"+saveFileName;
			log.info("ext : "+ ext);
			log.info("saveFileName : "+ saveFileName);
			log.info("saveFilePath : "+ saveFilePath);
			log.info("fullFileInfo : "+ fullFileInfo);
			mf.transferTo(new File(fullFileInfo));
			OcrDTO pDTO = new OcrDTO();
			pDTO.setFileName(saveFileName);
			pDTO.setFilePath(saveFilePath);
			OcrDTO rDTO = ocrService.getReadforImageText(pDTO);
			if(rDTO ==null) {
				rDTO = new OcrDTO();
			}
			res = CmmUtil.nvl(rDTO.getTextFromImage());
			rDTO = null;
			pDTO = null;
			
		}else {
			res = "이미지 파일이 아니라서 인식이 불가능합니다.";
		}
		model.addAttribute("res", res);
		log.info(this.getClass().getName() + ".getReadforImage end!");
		return "/ocr/TextFromImage";
	}
	
}
