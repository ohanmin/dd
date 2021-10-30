package poly.service;

import poly.dto.OcrDTO;

public interface IOcrService {
	OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception;
}
