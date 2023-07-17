package com.block6pathvariableheaders.block6pathvariableheaders.Repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllData {
    private String body;
    private List<String> headers;
    private List<String> requestParams;


}
