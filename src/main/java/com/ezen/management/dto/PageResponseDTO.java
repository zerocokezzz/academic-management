package com.ezen.management.dto;

import lombok.*;

import java.util.List;


@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;

//    페이지당 개수 요청 따로 없으면 10개씩
    private int size = 10;

    private int total;

//    시작 페이지
    private int start;

//    끝
    private int end;

    private boolean prev;
    private boolean next;

    private List<E> dtoList;

//    PageResponseDTO.<Member>withAll 로 사용할 수 있음
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        if(total <= 0){
            return;
        }

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

//        화면에서  <  1 2 3 4 5 6 7 8 9 10 > 이렇게 보여주니까 end는 마지막 출력될 페이지 버튼, start는 처음 출력될 페이지 버튼
        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;
        this.start = this.end - 9;

//        마지막 페이지 버튼
        int last = (int)(Math.ceil((total/(double)size)));

        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }
}
