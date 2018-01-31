package com.shop.api.web.controller.shop;

import com.shop.api.web.dto.ConsumeDto;
import com.shop.api.web.dto.MemberDto;
import com.shop.api.web.model.common.Result;
import com.shop.api.web.model.member.MemberDiscount;
import com.shop.api.web.model.member.MemberInfo;
import com.shop.api.web.service.member.MemberDiscountService;
import com.shop.api.web.service.member.MemberInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * created by ygl on 2018/1/26
 */
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberUserController {

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private MemberDiscountService memberDiscountService;

    /**
     * 通过phone获取会员信息
     * @param phone
     * @return
     */
    @GetMapping("/phone/{phone}")
    public Result findByPhone(@PathVariable @NotBlank String phone){
        List<MemberInfo> members = memberInfoService.findByPhoneFromMongo(phone);

        if (CollectionUtils.isEmpty(members)) {
            return Result.errorResult(" 未找到对应的会员信息");
        }

        List<MemberDto> memberDtos = new ArrayList<>();

        for (MemberInfo member : members) {
            Long memberId = member.getId();
            double discount = 1;
            // 获取会员的折扣信息
            List<MemberDiscount> memberDiscounts = memberDiscountService.findByMemberIdFromMongo(memberId);
            for (MemberDiscount memberDiscount : memberDiscounts) {
                Integer discountSpecific = memberDiscount.getDiscountSpecific();
                // 获取会员折扣
                if (discountSpecific != null && discountSpecific.intValue() == 1) {
                    double discountRate = memberDiscount.getDiscountRate();
                    if (discountRate < discount) {
                        discount = discountRate;
                    }
                }
            }
            MemberDto memberDto = new MemberDto(member, discount);
            memberDtos.add(memberDto);
        }

        return Result.okResult(memberDtos);
    }

    /**
     * 消费
     * @param consumeDto
     * @return
     */
    @PostMapping("/consume")
    public Result consume(@RequestBody @Valid ConsumeDto consumeDto){
        // 扣款时check
        Long memberId = consumeDto.getMemberId();
        BigDecimal consumeMoney = consumeDto.getConsumeMoney();
        Double discount = consumeDto.getDiscount();
        MemberInfo memberInfo = memberInfoService.findByIdFromMongo(memberId);
        BigDecimal storeAccount = memberInfo.getStoreAccount();
        int compareTo = storeAccount.compareTo(consumeMoney);
        if (compareTo < 0 ){
            return Result.errorResult("余额不足");
        }
        // 有折扣时判断会员折扣是否存在
        if (discount != null && discount < 1 && discount > 0) {
            // 是否存在该折扣
            boolean flag = false;
            List<MemberDiscount> memberDiscounts = memberDiscountService.findByMemberIdFromMongo(memberId);
            for (MemberDiscount memberDiscount : memberDiscounts) {
                Integer discountSpecific = memberDiscount.getDiscountSpecific();
                // 获取会员折扣
                if (discountSpecific != null && discountSpecific.intValue() == 1) {
                    double discountRate = memberDiscount.getDiscountRate();
                    if (discount.doubleValue() == discountRate) {
                        flag = true;
                    }
                }
            }
            if (!flag) {
                return Result.errorResult("折扣错误");
            }
        }
        // 执行扣款操作
        memberInfo.setStoreAccount(storeAccount.subtract(consumeMoney));
        memberInfoService.saveToMongo(memberInfo);
        memberInfoService.save(memberInfo);
        log.info("扣款成功:"+consumeMoney);
        return Result.okResult("扣款成功:"+consumeMoney);
    }
}
