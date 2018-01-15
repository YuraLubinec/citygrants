package com.warmcity.citygrants.dto;

import com.warmcity.citygrants.models.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProjectApplJuryDTO implements Serializable {

    private String id;
    private Description description;
    private Budget budget;
    private Evaluation evaluation;
    private List<Comment> comments;
    private boolean confirmed;
    private List<FileInfo> filesInfo;
}
