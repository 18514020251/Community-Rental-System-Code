package com.alibaba.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("house_locations")
@ApiModel(value="HouseLocations对象", description="")
public class HouseLocations implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "location_id", type = IdType.AUTO)
    private Long locationId;

    @TableField("house_id")
    private Long houseId;

    @TableField("district")
    private String district;

    @TableField("area_name")
    private String areaName;

    @TableField("landmark")
    private String landmark;

    @TableField("longitude")
    private BigDecimal longitude;

    @TableField("latitude")
    private BigDecimal latitude;


}
