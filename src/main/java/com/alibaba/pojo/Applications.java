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
@TableName("applications")
@ApiModel(value="Applications对象", description="")
public class Applications implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "application_id", type = IdType.AUTO)
    private Long applicationId;

    @TableField("applicant_id")
    private Long applicantId;

    @TableField("application_type")
    private String applicationType;

    @TableField("application_data")
    private String applicationData;

    @TableField("status")
    private String status;

    @TableField("handled_by")
    private Long handledBy;

    @TableField("handled_at")
    private LocalDateTime handledAt;

    @TableField("reason")
    private String reason;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("is_deleted")
    private Boolean isDeleted;


}
