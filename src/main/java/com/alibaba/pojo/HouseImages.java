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
@TableName("house_images")
@ApiModel(value="HouseImages对象", description="")
public class HouseImages implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "image_id", type = IdType.AUTO)
    private Long imageId;

    @TableField("house_id")
    private Long houseId;

    @TableField("image_url")
    private String imageUrl;

    @TableField("sort_order")
    private Integer sortOrder;

    @TableField("is_primary")
    private Boolean isPrimary;

    @TableField("created_at")
    private LocalDateTime createdAt;


}
