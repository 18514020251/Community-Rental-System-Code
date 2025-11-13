package com.alibaba.pojo;

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
@TableName("dict_layout")
@ApiModel(value="DictLayout对象", description="")
public class DictLayout implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "layout_id", type = IdType.AUTO)
    private Integer layoutId;

    @TableField("layout_name")
    private String layoutName;

    @TableField("bedroom_count")
    private Integer bedroomCount;

    @TableField("living_room_count")
    private Integer livingRoomCount;


}
