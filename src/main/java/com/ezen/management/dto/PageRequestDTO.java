package com.ezen.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type;

    private String keyword;

    private String link;

    public Pageable getPageable(String...props){
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    public String getLink(){

        StringBuilder sb = new StringBuilder();

        if(link == null){
            sb.append("page=").append(this.page)
                    .append("&size=").append(this.size);
        }

        if(type != null && type.length() > 0){
            sb.append("&type=").append(type);
        }

        if(keyword != null){
            sb.append("&keyword=").append(URLEncoder.encode(this.keyword, StandardCharsets.UTF_8));
        }

        return sb.toString();
    }

}
