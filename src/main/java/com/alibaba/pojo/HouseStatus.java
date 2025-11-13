package com.alibaba.pojo;

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
@TableName("house_status")
@ApiModel(value="HouseStatus对象", description="")
public class HouseStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "status_id", type = IdType.AUTO)
    private Long statusId;

    @TableField("house_id")
    private Long houseId;

    @TableField("status")
    private String status;

    @TableField("view_count")
    private Integer viewCount;

    @TableField("favorite_count")
    private Integer favoriteCount;

    @TableField("is_new")
    private Boolean isNew;

    @TableField("publish_time")
    private LocalDateTime publishTime;


}
