package com.cdac.scanmark.dto ;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LectureResponse {
    private Long id;
    private String subjectName;
    private LocalDateTime lectureTime;

}
