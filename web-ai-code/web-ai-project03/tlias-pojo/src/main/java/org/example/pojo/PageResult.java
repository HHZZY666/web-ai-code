package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;
}
