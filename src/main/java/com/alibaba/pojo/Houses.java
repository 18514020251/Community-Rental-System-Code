package com.alibaba.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author sister
 * @since 2025-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("houses")
@ApiModel(value="Houses对象", description="")
public class Houses implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "house_id", type = IdType.AUTO)
    private Long houseId;

    @TableField("title")
    private String title;

    @TableField("price")
    private BigDecimal price;

    @TableField("layout_id")
    private Integer layoutId;

    @TableField("area")
    private BigDecimal area;

    @TableField("user_id")
    private Long userId;

    @TableField("orientation_id")
    private Integer orientationId;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("is_deleted")
    private Boolean isDeleted;


}
