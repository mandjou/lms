package com.kofianan.lms.dashboard.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kofianan.lms.dashboard.security.SecurityUtils;
import com.kofianan.lms.dashboard.service.dto.UserImageDTO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class UserImageService {
	private static final Logger log = LoggerFactory.getLogger(UserImageService.class);
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	public boolean addProfileImage(String filename, String mimetype, MultipartFile file) {
		List<GridFSDBFile> imageLists = gridFsTemplate.find(new Query(Criteria.where("metadata.login").is(SecurityUtils.getCurrentUserLogin())));
		
		for (GridFSDBFile profileImage : imageLists) {
			gridFsTemplate.delete(new Query(Criteria.where("_id").is(profileImage.getId())));
		}
		
		try {
			DBObject metadata = new BasicDBObject();
			metadata.put("login", SecurityUtils.getCurrentUserLogin());
			metadata.put("uploaded_by", SecurityUtils.getCurrentUserLogin());
			String id = gridFsTemplate.store(file.getInputStream(), filename, mimetype, metadata).getId().toString();
			
			if (!id.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
	
	public UserImageDTO getProfileImage() {
		UserImageDTO  userImageDTO = new UserImageDTO();
		
		GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(new Query(Criteria.where("metadata.login").is(SecurityUtils.getCurrentUserLogin())));
		if (gridFSDBFile != null) {
			if (gridFSDBFile.getId() != null && gridFSDBFile.getChunkSize() > 0L) {
				byte[] content = null;
				try {
					content = StreamUtils.copyToByteArray(gridFSDBFile.getInputStream());
					if (content.length > 0) {
						userImageDTO.setContent(content);
						userImageDTO.setContentLength(gridFSDBFile.getLength());
						userImageDTO.setContentType(gridFSDBFile.getContentType());
						userImageDTO.setFileName(gridFSDBFile.getFilename());
					}
				} catch (Exception e) {
					log.info("error getting user profile image");
				}
			}
		}
		return userImageDTO;
		
	}
}
