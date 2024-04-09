package com.ezen.management.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private String id;
    private String pwd;
    private String name;
    private String uuid;
    private String fileName;

}
