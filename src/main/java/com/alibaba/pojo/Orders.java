package com.alibaba.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
@TableName("orders")
@ApiModel(value="Orders对象", description="")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @TableField("order_number")
    private String orderNumber;

    @TableField("house_id")
    private Long houseId;

    @TableField("tenant_id")
    private Long tenantId;

    @TableField("total_price")
    private BigDecimal totalPrice;

    @TableField("monthly_rent")
    private BigDecimal monthlyRent;

    @TableField("lease_start")
    private LocalDate leaseStart;

    @TableField("lease_end")
    private LocalDate leaseEnd;

    @TableField("order_status")
    private String orderStatus;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("is_deleted")
    private Boolean isDeleted;


}
