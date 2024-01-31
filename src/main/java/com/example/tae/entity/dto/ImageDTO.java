package com.example.tae.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 자바에서 메일을보내기위한 정보를 받는 객체 (추후 추가가능)
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    public String ordercode;
    public String imageDataURL;
    public String emailadress;
    public String departName;
}
