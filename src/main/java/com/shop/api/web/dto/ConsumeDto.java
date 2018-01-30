package com.shop.api.web.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ConsumeDto {
    /**
     * 会员ID
     */
    @NotNull(message = "会员ID不能为空")
    private Long memberId;
    /**
     * 储值卡消费金额
     */
    @NotNull(message = "消费金额不能为空")
    private BigDecimal consumeMoney;

}
