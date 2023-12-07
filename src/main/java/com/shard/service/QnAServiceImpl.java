package com.shard.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shard.domain.PageVO;
import com.shard.domain.QnAVO;
import com.shard.mapper.QnAMapper;
import com.shard.util.ImgUtil;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnAMapper mapper;
	
	private ImgUtil util = ImgUtil.getInstence();

	@Override
	public List<QnAVO> qnaList(PageVO vo) {
		return mapper.qnaList(vo);
	}

	@Override
	public int qnaInsert(QnAVO vo, MultipartFile file) {
		int result = 0;
		
		// 파일이 비어 있는지 확인
		if (file.isEmpty()) {
			result = -1; // 파일 업로드 성공! and 데이터베이스에 저장 성공	
			return result;
		}

		// 파일 크기 제한
		if (file.getSize() > 5 * 1024 * 1024) { // 5MB 제한 (설정 가능)
			result = -1; // 파일 크기는 5MB를 초과할 수 없습니다.
			return result;
		}

		// 파일 확장자 확인
		String fileName = file.getOriginalFilename();
		if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")) {
			result = -2; // 지원하지 않는 파일 형식입니다. (.jpg, .jpeg, .png만 허용)
			return result;
		}

		String success = util.storeFile(file);
		
		if (success != null) {
			vo.setReplyImg("/img/"+success);
			mapper.qnaInsert(vo);
			result = 1; // 파일 업로드 성공! and 데이터베이스에 저장 성공
		}

		return result;
	}

	@Override
	public int delete(String email) {
		return mapper.delete(email);
	}

	@Override
	public int totalCount() {
		return mapper.totalCount();
	}

	

	@Override
	public int getReplyCheck(int replyNum, String replyPwd) {
		int result = 0;
		String pwd = mapper.getReplyPwd(replyNum);
		if(pwd != null) {
			if(pwd.equals(replyPwd)) {
				result = 1;
			}else {
				result = 0;
			}
		}
		return result;
	}

	@Override
	public List<QnAVO> getQnAList(String email) {
		return mapper.getQnAList(email);
	}

	@Override
	public QnAVO getQnA(int replyNum) {
		return mapper.getQnA(replyNum);
	}

	@Override
	public int qnaInsert(QnAVO vo) {
		return mapper.qnaInsert(vo);
	}


}
