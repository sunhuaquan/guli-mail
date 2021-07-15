package com.sun.mail.member.dto;

import com.sun.mail.member.valid.AddGroup;
import com.sun.mail.member.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class MemberDto {

    @NotNull(message = "修改必须传id", groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id", groups = {AddGroup.class})
    private Long id;

    private String name;
}
