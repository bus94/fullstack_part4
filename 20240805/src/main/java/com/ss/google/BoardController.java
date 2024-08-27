package com.ss.google;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class BoardController {

	@RequestMapping("/board/fileUpload")
	public String fileUpload() {
		System.out.println("fileUpload() 실행");

		return "board/fileUpload";
	}

	// 내 컴퓨터 드라이버에 저장하는 매핑
	@RequestMapping("/board/upload_ok")
	public String fileUploadOk(@RequestParam("file") MultipartFile file) {
		System.out.println("upload_ok() 실행");

		try {
			// 파일명을 얻어서 출력
			String fileReadName = file.getOriginalFilename();
			// 파일 사이즈
			long size = file.getSize();

			System.out.println("파일명: " + fileReadName);
			System.out.println("파일 사이즈: " + size);

			// 서버에 저장할 파일이름 확장자 이용해서 저장
			String fileReName  = fileReadName.substring(fileReadName.lastIndexOf("."));
			// 파일을 업로드 하는 위치를 지정
			String uploadFolder = "C:\\test\\upload";

			// 업로드 하는 폴더가 없을 경우
			File uploadDir = new File(uploadFolder);

			// 폴더 있으면 만들지 않고 폴더 없으면 만들 수 있는 예외를 작성
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// 저장할 파일 경로
			File saveFile = new File(uploadFolder + "\\" + fileReadName);

			// 파일 저장
			file.transferTo(saveFile);
			System.out.println("파일 저장 성공");

		} catch (Exception e) {
			System.out.println("파일 저장 실패");
			e.printStackTrace();
		}

		return "board/fileUpload";
	}
	
	// 톰캣 서버에 저장하기 (업로드)
	// 서버에 저장될 경로를 가지고 온다.
	@Autowired
	private ServletContext context;
	
	@RequestMapping("/board/upload_ok2")
	public String fileUploadOk2(@RequestParam("file") MultipartFile file) {
		System.out.println("upload_ok2() 실행");

		try {
			// 파일명을 얻어서 출력
			String fileReadName = file.getOriginalFilename();
			// 파일 사이즈
			long size = file.getSize();

			System.out.println("파일명: " + fileReadName);
			System.out.println("파일 사이즈: " + size);

			// 서버에 저장할 파일이름 확장자 이용해서 저장
			String fileReName  = fileReadName.substring(fileReadName.lastIndexOf("."));
			// 파일을 업로드 하는 위치를 지정
			String uploadFolder = context.getRealPath("/resources/upload");

			// 업로드 하는 폴더가 없을 경우
			File uploadDir = new File(uploadFolder);

			// 폴더 있으면 만들지 않고 폴더 없으면 만들 수 있는 예외를 작성
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// 저장할 파일 경로
			File saveFile = new File(uploadFolder + File.separator + fileReadName);

			// 파일 저장
			file.transferTo(saveFile);
			System.out.println("파일 저장 성공");

		} catch (Exception e) {
			System.out.println("파일 저장 실패");
			e.printStackTrace();
		}

		return "board/fileUpload";
	}
	
	@RequestMapping("/board/upload_ok3")
	public String fileUploadOk3(MultipartHttpServletRequest files) {
		System.out.println("upload_ok3() 실행");
		System.out.println("files: " + files);

		try {
			// 파일을 업로드 하는 위치를 지정
			String uploadFolder = context.getRealPath("/resources/upload");

			// 업로드 하는 폴더가 없을 경우
			File uploadDir = new File(uploadFolder);

			// 폴더 있으면 만들지 않고 폴더 없으면 만들 수 있는 예외를 작성
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			List<MultipartFile> list = files.getFiles("files");
			
			for(int i = 0; i < list.size(); i++) {
				String fileReadName = list.get(i).getOriginalFilename();
				long size = list.get(i).getSize();
				
				System.out.println((i+1) + ".파일명: " + fileReadName);
				System.out.println((i+1) + ".파일 사이즈: " + size);
				
				// 저장할 파일 경로
				File saveFile = new File(uploadFolder + File.separator + fileReadName);
				
				// 파일 저장
				list.get(i).transferTo(saveFile);
				System.out.println((i+1) + "번 파일 저장 성공!!");
			}

		} catch (Exception e) {
			System.out.println("파일 저장 실패");
			e.printStackTrace();
		}

		return "board/fileUpload";
	}

}
