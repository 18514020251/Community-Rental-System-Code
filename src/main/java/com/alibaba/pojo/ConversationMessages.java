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
@TableName("conversation_messages")
@ApiModel(value="ConversationMessages对象", description="")
public class ConversationMessages implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;

    @TableField("conversation_id")
    private Long conversationId;

    @TableField("sender_id")
    private Long senderId;

    @TableField("message_type")
    private String messageType;

    @TableField("content")
    private String content;

    @TableField("is_read")
    private Boolean isRead;

    @TableField("read_time")
    private LocalDateTime readTime;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("is_deleted")
    private Boolean isDeleted;


}
