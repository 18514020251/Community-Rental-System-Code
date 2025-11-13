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
@TableName("conversations")
@ApiModel(value="Conversations对象", description="")
public class Conversations implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "conversation_id", type = IdType.AUTO)
    private Long conversationId;

    @TableField("participant1_id")
    private Long participant1Id;

    @TableField("participant2_id")
    private Long participant2Id;

    @TableField("house_id")
    private Long houseId;

    @TableField("last_message")
    private String lastMessage;

    @TableField("last_message_time")
    private LocalDateTime lastMessageTime;

    @TableField("unread_count_p1")
    private Integer unreadCountP1;

    @TableField("unread_count_p2")
    private Integer unreadCountP2;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;


}
