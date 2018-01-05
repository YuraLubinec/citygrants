package com.warmcity.citygrants.dto;

import com.warmcity.citygrants.models.Budget;
import com.warmcity.citygrants.models.Comment;
import com.warmcity.citygrants.models.Description;
import com.warmcity.citygrants.models.Evaluation;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class ProjectApplJuryDTO implements Serializable {
    private Description description;
    private Budget budget;
    private Evaluation evaluation;
    private List<Comment> comments;
    private boolean confirmed;
}
