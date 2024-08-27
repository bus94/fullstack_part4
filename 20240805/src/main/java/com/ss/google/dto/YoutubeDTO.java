package com.ss.google.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class YoutubeDTO {
	// 동영상을 재생할 수 있는 아이디
	private String videoId;
	private String title;
	private String description;
	private String thumbnails;
	private String kind;
}
