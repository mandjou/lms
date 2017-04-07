package com.kofianan.lms.dashboard.service.dto;

import lombok.Data;

@Data
public class UserImageDTO {
	private String fileName;
	private long contentLength;
	private String contentType;
	private byte[] content;
}
